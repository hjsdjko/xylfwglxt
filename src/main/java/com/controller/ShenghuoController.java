
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
 * 生活照料
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shenghuo")
public class ShenghuoController {
    private static final Logger logger = LoggerFactory.getLogger(ShenghuoController.class);

    private static final String TABLE_NAME = "shenghuo";

    @Autowired
    private ShenghuoService shenghuoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private BaojianService baojianService;//医疗保健
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
        params.put("shenghuoDeleteStart",1);params.put("shenghuoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = shenghuoService.queryPage(params);

        //字典表数据转换
        List<ShenghuoView> list =(List<ShenghuoView>)page.getList();
        for(ShenghuoView c:list){
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
        ShenghuoEntity shenghuo = shenghuoService.selectById(id);
        if(shenghuo !=null){
            //entity转view
            ShenghuoView view = new ShenghuoView();
            BeanUtils.copyProperties( shenghuo , view );//把实体数据重构到view中
            //级联表 老人
            //级联表
            LaorenEntity laoren = laorenService.selectById(shenghuo.getLaorenId());
            if(laoren != null){
            BeanUtils.copyProperties( laoren , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "laorenId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody ShenghuoEntity shenghuo, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shenghuo:{}",this.getClass().getName(),shenghuo.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老人".equals(role))
            shenghuo.setLaorenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShenghuoEntity> queryWrapper = new EntityWrapper<ShenghuoEntity>()
            .eq("laoren_id", shenghuo.getLaorenId())
            .eq("shenghuo_name", shenghuo.getShenghuoName())
            .eq("shenghuo_address", shenghuo.getShenghuoAddress())
            .eq("shenghuo_types", shenghuo.getShenghuoTypes())
            .eq("shenghuo_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenghuoEntity shenghuoEntity = shenghuoService.selectOne(queryWrapper);
        if(shenghuoEntity==null){
            shenghuo.setShenghuoDelete(1);
            shenghuo.setInsertTime(new Date());
            shenghuo.setCreateTime(new Date());
            shenghuoService.insert(shenghuo);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShenghuoEntity shenghuo, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shenghuo:{}",this.getClass().getName(),shenghuo.toString());
        ShenghuoEntity oldShenghuoEntity = shenghuoService.selectById(shenghuo.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老人".equals(role))
//            shenghuo.setLaorenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(shenghuo.getShenghuoPhoto()) || "null".equals(shenghuo.getShenghuoPhoto())){
                shenghuo.setShenghuoPhoto(null);
        }

            shenghuoService.updateById(shenghuo);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShenghuoEntity> oldShenghuoList =shenghuoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ShenghuoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ShenghuoEntity shenghuoEntity = new ShenghuoEntity();
            shenghuoEntity.setId(id);
            shenghuoEntity.setShenghuoDelete(2);
            list.add(shenghuoEntity);
        }
        if(list != null && list.size() >0){
            shenghuoService.updateBatchById(list);
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
            List<ShenghuoEntity> shenghuoList = new ArrayList<>();//上传的东西
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
                            ShenghuoEntity shenghuoEntity = new ShenghuoEntity();
//                            shenghuoEntity.setLaorenId(Integer.valueOf(data.get(0)));   //老人 要改的
//                            shenghuoEntity.setShenghuoName(data.get(0));                    //生活照料名称 要改的
//                            shenghuoEntity.setShenghuoUuidNumber(data.get(0));                    //生活照料编号 要改的
//                            shenghuoEntity.setShenghuoPhoto("");//详情和图片
//                            shenghuoEntity.setShenghuoAddress(data.get(0));                    //生活照料地点 要改的
//                            shenghuoEntity.setShenghuoTypes(Integer.valueOf(data.get(0)));   //生活照料类型 要改的
//                            shenghuoEntity.setShenghuoContent("");//详情和图片
//                            shenghuoEntity.setShenghuoDelete(1);//逻辑删除字段
//                            shenghuoEntity.setInsertTime(date);//时间
//                            shenghuoEntity.setCreateTime(date);//时间
                            shenghuoList.add(shenghuoEntity);


                            //把要查询是否重复的字段放入map中
                                //生活照料编号
                                if(seachFields.containsKey("shenghuoUuidNumber")){
                                    List<String> shenghuoUuidNumber = seachFields.get("shenghuoUuidNumber");
                                    shenghuoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shenghuoUuidNumber = new ArrayList<>();
                                    shenghuoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shenghuoUuidNumber",shenghuoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //生活照料编号
                        List<ShenghuoEntity> shenghuoEntities_shenghuoUuidNumber = shenghuoService.selectList(new EntityWrapper<ShenghuoEntity>().in("shenghuo_uuid_number", seachFields.get("shenghuoUuidNumber")).eq("shenghuo_delete", 1));
                        if(shenghuoEntities_shenghuoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShenghuoEntity s:shenghuoEntities_shenghuoUuidNumber){
                                repeatFields.add(s.getShenghuoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [生活照料编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shenghuoService.insertBatch(shenghuoList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ShenghuoView> returnShenghuoViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = shenghuoOrderService.queryPage(params1);
        List<ShenghuoOrderView> orderViewsList =(List<ShenghuoOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ShenghuoOrderView orderView:orderViewsList){
            Integer shenghuoTypes = orderView.getShenghuoTypes();
            if(typeMap.containsKey(shenghuoTypes)){
                typeMap.put(shenghuoTypes,typeMap.get(shenghuoTypes)+1);
            }else{
                typeMap.put(shenghuoTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("shenghuoTypes",type);
            PageUtils pageUtils1 = shenghuoService.queryPage(params2);
            List<ShenghuoView> shenghuoViewList =(List<ShenghuoView>)pageUtils1.getList();
            returnShenghuoViewList.addAll(shenghuoViewList);
            if(returnShenghuoViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = shenghuoService.queryPage(params);
        if(returnShenghuoViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnShenghuoViewList.size();//要添加的数量
            List<ShenghuoView> shenghuoViewList =(List<ShenghuoView>)page.getList();
            for(ShenghuoView shenghuoView:shenghuoViewList){
                Boolean addFlag = true;
                for(ShenghuoView returnShenghuoView:returnShenghuoViewList){
                    if(returnShenghuoView.getId().intValue() ==shenghuoView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnShenghuoViewList.add(shenghuoView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnShenghuoViewList = returnShenghuoViewList.subList(0, limit);
        }

        for(ShenghuoView c:returnShenghuoViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnShenghuoViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shenghuoService.queryPage(params);

        //字典表数据转换
        List<ShenghuoView> list =(List<ShenghuoView>)page.getList();
        for(ShenghuoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShenghuoEntity shenghuo = shenghuoService.selectById(id);
            if(shenghuo !=null){


                //entity转view
                ShenghuoView view = new ShenghuoView();
                BeanUtils.copyProperties( shenghuo , view );//把实体数据重构到view中

                //级联表
                    LaorenEntity laoren = laorenService.selectById(shenghuo.getLaorenId());
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
    public R add(@RequestBody ShenghuoEntity shenghuo, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shenghuo:{}",this.getClass().getName(),shenghuo.toString());
        Wrapper<ShenghuoEntity> queryWrapper = new EntityWrapper<ShenghuoEntity>()
            .eq("laoren_id", shenghuo.getLaorenId())
            .eq("shenghuo_name", shenghuo.getShenghuoName())
            .eq("shenghuo_uuid_number", shenghuo.getShenghuoUuidNumber())
            .eq("shenghuo_address", shenghuo.getShenghuoAddress())
            .eq("shenghuo_types", shenghuo.getShenghuoTypes())
            .eq("shenghuo_delete", shenghuo.getShenghuoDelete())
//            .notIn("shenghuo_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenghuoEntity shenghuoEntity = shenghuoService.selectOne(queryWrapper);
        if(shenghuoEntity==null){
            shenghuo.setShenghuoDelete(1);
            shenghuo.setInsertTime(new Date());
            shenghuo.setCreateTime(new Date());
        shenghuoService.insert(shenghuo);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

