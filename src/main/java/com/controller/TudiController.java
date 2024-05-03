
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
 * 土地
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tudi")
public class TudiController {
    private static final Logger logger = LoggerFactory.getLogger(TudiController.class);

    private static final String TABLE_NAME = "tudi";

    @Autowired
    private TudiService tudiService;


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
    private ShenghuoService shenghuoService;//生活照料
    @Autowired
    private ShenghuoOrderService shenghuoOrderService;//生活照料订单
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
        params.put("tudiDeleteStart",1);params.put("tudiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = tudiService.queryPage(params);

        //字典表数据转换
        List<TudiView> list =(List<TudiView>)page.getList();
        for(TudiView c:list){
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
        TudiEntity tudi = tudiService.selectById(id);
        if(tudi !=null){
            //entity转view
            TudiView view = new TudiView();
            BeanUtils.copyProperties( tudi , view );//把实体数据重构到view中
            //级联表 老人
            //级联表
            LaorenEntity laoren = laorenService.selectById(tudi.getLaorenId());
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
    public R save(@RequestBody TudiEntity tudi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tudi:{}",this.getClass().getName(),tudi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老人".equals(role))
            tudi.setLaorenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<TudiEntity> queryWrapper = new EntityWrapper<TudiEntity>()
            .eq("laoren_id", tudi.getLaorenId())
            .eq("tudi_name", tudi.getTudiName())
            .eq("tudi_address", tudi.getTudiAddress())
            .eq("tudi_types", tudi.getTudiTypes())
            .eq("tudi_kucun_number", tudi.getTudiKucunNumber())
            .eq("tudi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TudiEntity tudiEntity = tudiService.selectOne(queryWrapper);
        if(tudiEntity==null){
            tudi.setTudiDelete(1);
            tudi.setInsertTime(new Date());
            tudi.setCreateTime(new Date());
            tudiService.insert(tudi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TudiEntity tudi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,tudi:{}",this.getClass().getName(),tudi.toString());
        TudiEntity oldTudiEntity = tudiService.selectById(tudi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老人".equals(role))
//            tudi.setLaorenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(tudi.getTudiPhoto()) || "null".equals(tudi.getTudiPhoto())){
                tudi.setTudiPhoto(null);
        }

            tudiService.updateById(tudi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<TudiEntity> oldTudiList =tudiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<TudiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            TudiEntity tudiEntity = new TudiEntity();
            tudiEntity.setId(id);
            tudiEntity.setTudiDelete(2);
            list.add(tudiEntity);
        }
        if(list != null && list.size() >0){
            tudiService.updateBatchById(list);
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
            List<TudiEntity> tudiList = new ArrayList<>();//上传的东西
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
                            TudiEntity tudiEntity = new TudiEntity();
//                            tudiEntity.setLaorenId(Integer.valueOf(data.get(0)));   //商家 要改的
//                            tudiEntity.setTudiName(data.get(0));                    //土地名称 要改的
//                            tudiEntity.setTudiUuidNumber(data.get(0));                    //土地编号 要改的
//                            tudiEntity.setTudiPhoto("");//详情和图片
//                            tudiEntity.setTudiAddress(data.get(0));                    //土地地点 要改的
//                            tudiEntity.setTudiTypes(Integer.valueOf(data.get(0)));   //土地类型 要改的
//                            tudiEntity.setTudiKucunNumber(Integer.valueOf(data.get(0)));   //多少亩 要改的
//                            tudiEntity.setTudiNewMoney(data.get(0));                    //价格/年 要改的
//                            tudiEntity.setTudiContent("");//详情和图片
//                            tudiEntity.setTudiDelete(1);//逻辑删除字段
//                            tudiEntity.setInsertTime(date);//时间
//                            tudiEntity.setCreateTime(date);//时间
                            tudiList.add(tudiEntity);


                            //把要查询是否重复的字段放入map中
                                //土地编号
                                if(seachFields.containsKey("tudiUuidNumber")){
                                    List<String> tudiUuidNumber = seachFields.get("tudiUuidNumber");
                                    tudiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tudiUuidNumber = new ArrayList<>();
                                    tudiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tudiUuidNumber",tudiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //土地编号
                        List<TudiEntity> tudiEntities_tudiUuidNumber = tudiService.selectList(new EntityWrapper<TudiEntity>().in("tudi_uuid_number", seachFields.get("tudiUuidNumber")).eq("tudi_delete", 1));
                        if(tudiEntities_tudiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TudiEntity s:tudiEntities_tudiUuidNumber){
                                repeatFields.add(s.getTudiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [土地编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tudiService.insertBatch(tudiList);
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
        List<TudiView> returnTudiViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = tudiOrderService.queryPage(params1);
        List<TudiOrderView> orderViewsList =(List<TudiOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(TudiOrderView orderView:orderViewsList){
            Integer tudiTypes = orderView.getTudiTypes();
            if(typeMap.containsKey(tudiTypes)){
                typeMap.put(tudiTypes,typeMap.get(tudiTypes)+1);
            }else{
                typeMap.put(tudiTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("tudiTypes",type);
            PageUtils pageUtils1 = tudiService.queryPage(params2);
            List<TudiView> tudiViewList =(List<TudiView>)pageUtils1.getList();
            returnTudiViewList.addAll(tudiViewList);
            if(returnTudiViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = tudiService.queryPage(params);
        if(returnTudiViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnTudiViewList.size();//要添加的数量
            List<TudiView> tudiViewList =(List<TudiView>)page.getList();
            for(TudiView tudiView:tudiViewList){
                Boolean addFlag = true;
                for(TudiView returnTudiView:returnTudiViewList){
                    if(returnTudiView.getId().intValue() ==tudiView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnTudiViewList.add(tudiView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnTudiViewList = returnTudiViewList.subList(0, limit);
        }

        for(TudiView c:returnTudiViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnTudiViewList);
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
        PageUtils page = tudiService.queryPage(params);

        //字典表数据转换
        List<TudiView> list =(List<TudiView>)page.getList();
        for(TudiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TudiEntity tudi = tudiService.selectById(id);
            if(tudi !=null){


                //entity转view
                TudiView view = new TudiView();
                BeanUtils.copyProperties( tudi , view );//把实体数据重构到view中

                //级联表
                    LaorenEntity laoren = laorenService.selectById(tudi.getLaorenId());
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
    public R add(@RequestBody TudiEntity tudi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tudi:{}",this.getClass().getName(),tudi.toString());
        Wrapper<TudiEntity> queryWrapper = new EntityWrapper<TudiEntity>()
            .eq("laoren_id", tudi.getLaorenId())
            .eq("tudi_name", tudi.getTudiName())
            .eq("tudi_uuid_number", tudi.getTudiUuidNumber())
            .eq("tudi_address", tudi.getTudiAddress())
            .eq("tudi_types", tudi.getTudiTypes())
            .eq("tudi_kucun_number", tudi.getTudiKucunNumber())
            .eq("tudi_delete", tudi.getTudiDelete())
//            .notIn("tudi_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TudiEntity tudiEntity = tudiService.selectOne(queryWrapper);
        if(tudiEntity==null){
            tudi.setTudiDelete(1);
            tudi.setInsertTime(new Date());
            tudi.setCreateTime(new Date());
        tudiService.insert(tudi);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

