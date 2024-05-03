
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 医疗保健
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/baojian")
public class BaojianController {
    private static final Logger logger = LoggerFactory.getLogger(BaojianController.class);

    private static final String TABLE_NAME = "baojian";

    @Autowired
    private BaojianService baojianService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private HuodongService huodongService;//文娱活动
    @Autowired
    private HuodongYuyueService huodongYuyueService;//活动报名
    @Autowired
    private JiankangService jiankangService;//健康档案
    @Autowired
    private LaorenService laorenService;//老人
    @Autowired
    private ShenghuoService shenghuoService;//生活照料
    @Autowired
    private ShenghuoOrderService shenghuoOrderService;//生活照料订单
    @Autowired
    private TudiService tudiService;//土地
    @Autowired
    private TudiOrderService tudiOrderService;//土地承包
    @Autowired
    private YiliaoService yiliaoService;//医疗人员
    @Autowired
    private YonghuService yonghuService;//乡村志愿者
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("乡村志愿者".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("老人".equals(role))
            params.put("laorenId",request.getSession().getAttribute("userId"));
        else if("医疗人员".equals(role))
            params.put("yiliaoId",request.getSession().getAttribute("userId"));
        params.put("baojianDeleteStart",1);params.put("baojianDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = baojianService.queryPage(params);

        //字典表数据转换
        List<BaojianView> list =(List<BaojianView>)page.getList();
        for(BaojianView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BaojianEntity baojian = baojianService.selectById(id);
        if(baojian !=null){
            //entity转view
            BaojianView view = new BaojianView();
            BeanUtils.copyProperties( baojian , view );//把实体数据重构到view中
            //级联表 医疗人员
            //级联表
            YiliaoEntity yiliao = yiliaoService.selectById(baojian.getYiliaoId());
            if(yiliao != null){
            BeanUtils.copyProperties( yiliao , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "laorenId"
, "yiliaoId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYiliaoId(yiliao.getId());
            }
            //级联表 老人
            //级联表
            LaorenEntity laoren = laorenService.selectById(baojian.getLaorenId());
            if(laoren != null){
            BeanUtils.copyProperties( laoren , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "laorenId"
, "yiliaoId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLaorenId(laoren.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody BaojianEntity baojian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,baojian:{}",this.getClass().getName(),baojian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老人".equals(role))
            baojian.setLaorenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("医疗人员".equals(role))
            baojian.setYiliaoId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<BaojianEntity> queryWrapper = new EntityWrapper<BaojianEntity>()
            .eq("yiliao_id", baojian.getYiliaoId())
            .eq("laoren_id", baojian.getLaorenId())
            .eq("baojian_name", baojian.getBaojianName())
            .eq("baojian_address", baojian.getBaojianAddress())
            .eq("baojian_types", baojian.getBaojianTypes())
            .eq("baojian_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BaojianEntity baojianEntity = baojianService.selectOne(queryWrapper);
        if(baojianEntity==null){
            baojian.setBaojianDelete(1);
            baojian.setInsertTime(new Date());
            baojian.setCreateTime(new Date());
            baojianService.insert(baojian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BaojianEntity baojian, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,baojian:{}",this.getClass().getName(),baojian.toString());
        BaojianEntity oldBaojianEntity = baojianService.selectById(baojian.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老人".equals(role))
//            baojian.setLaorenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("医疗人员".equals(role))
//            baojian.setYiliaoId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(baojian.getBaojianPhoto()) || "null".equals(baojian.getBaojianPhoto())){
                baojian.setBaojianPhoto(null);
        }

            baojianService.updateById(baojian);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<BaojianEntity> oldBaojianList =baojianService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<BaojianEntity> list = new ArrayList<>();
        for(Integer id:ids){
            BaojianEntity baojianEntity = new BaojianEntity();
            baojianEntity.setId(id);
            baojianEntity.setBaojianDelete(2);
            list.add(baojianEntity);
        }
        if(list != null && list.size() >0){
            baojianService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<BaojianEntity> baojianList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            BaojianEntity baojianEntity = new BaojianEntity();
//                            baojianEntity.setYiliaoId(Integer.valueOf(data.get(0)));   //医疗人员 要改的
//                            baojianEntity.setLaorenId(Integer.valueOf(data.get(0)));   //老人 要改的
//                            baojianEntity.setBaojianName(data.get(0));                    //医疗保健名称 要改的
//                            baojianEntity.setBaojianUuidNumber(data.get(0));                    //医疗保健编号 要改的
//                            baojianEntity.setBaojianPhoto("");//详情和图片
//                            baojianEntity.setBaojianAddress(data.get(0));                    //医疗保健地点 要改的
//                            baojianEntity.setBaojianTypes(Integer.valueOf(data.get(0)));   //医疗保健类型 要改的
//                            baojianEntity.setBaojianContent("");//详情和图片
//                            baojianEntity.setBaojianDelete(1);//逻辑删除字段
//                            baojianEntity.setInsertTime(date);//时间
//                            baojianEntity.setCreateTime(date);//时间
                            baojianList.add(baojianEntity);


                            //把要查询是否重复的字段放入map中
                                //医疗保健编号
                                if(seachFields.containsKey("baojianUuidNumber")){
                                    List<String> baojianUuidNumber = seachFields.get("baojianUuidNumber");
                                    baojianUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> baojianUuidNumber = new ArrayList<>();
                                    baojianUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("baojianUuidNumber",baojianUuidNumber);
                                }
                        }

                        //查询是否重复
                         //医疗保健编号
                        List<BaojianEntity> baojianEntities_baojianUuidNumber = baojianService.selectList(new EntityWrapper<BaojianEntity>().in("baojian_uuid_number", seachFields.get("baojianUuidNumber")).eq("baojian_delete", 1));
                        if(baojianEntities_baojianUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(BaojianEntity s:baojianEntities_baojianUuidNumber){
                                repeatFields.add(s.getBaojianUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [医疗保健编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        baojianService.insertBatch(baojianList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = baojianService.queryPage(params);

        //字典表数据转换
        List<BaojianView> list =(List<BaojianView>)page.getList();
        for(BaojianView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BaojianEntity baojian = baojianService.selectById(id);
            if(baojian !=null){


                //entity转view
                BaojianView view = new BaojianView();
                BeanUtils.copyProperties( baojian , view );//把实体数据重构到view中

                //级联表
                    YiliaoEntity yiliao = yiliaoService.selectById(baojian.getYiliaoId());
                if(yiliao != null){
                    BeanUtils.copyProperties( yiliao , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYiliaoId(yiliao.getId());
                }
                //级联表
                    LaorenEntity laoren = laorenService.selectById(baojian.getLaorenId());
                if(laoren != null){
                    BeanUtils.copyProperties( laoren , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaorenId(laoren.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody BaojianEntity baojian, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,baojian:{}",this.getClass().getName(),baojian.toString());
        Wrapper<BaojianEntity> queryWrapper = new EntityWrapper<BaojianEntity>()
            .eq("yiliao_id", baojian.getYiliaoId())
            .eq("laoren_id", baojian.getLaorenId())
            .eq("baojian_name", baojian.getBaojianName())
            .eq("baojian_uuid_number", baojian.getBaojianUuidNumber())
            .eq("baojian_address", baojian.getBaojianAddress())
            .eq("baojian_types", baojian.getBaojianTypes())
            .eq("baojian_delete", baojian.getBaojianDelete())
//            .notIn("baojian_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BaojianEntity baojianEntity = baojianService.selectOne(queryWrapper);
        if(baojianEntity==null){
            baojian.setBaojianDelete(1);
            baojian.setInsertTime(new Date());
            baojian.setCreateTime(new Date());
        baojianService.insert(baojian);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

