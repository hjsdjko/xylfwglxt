
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
 * 生活照料订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shenghuoOrder")
public class ShenghuoOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ShenghuoOrderController.class);

    private static final String TABLE_NAME = "shenghuoOrder";

    @Autowired
    private ShenghuoOrderService shenghuoOrderService;


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
        CommonUtil.checkMap(params);
        PageUtils page = shenghuoOrderService.queryPage(params);

        //字典表数据转换
        List<ShenghuoOrderView> list =(List<ShenghuoOrderView>)page.getList();
        for(ShenghuoOrderView c:list){
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
        ShenghuoOrderEntity shenghuoOrder = shenghuoOrderService.selectById(id);
        if(shenghuoOrder !=null){
            //entity转view
            ShenghuoOrderView view = new ShenghuoOrderView();
            BeanUtils.copyProperties( shenghuoOrder , view );//把实体数据重构到view中
            //级联表 生活照料
            //级联表
            ShenghuoEntity shenghuo = shenghuoService.selectById(shenghuoOrder.getShenghuoId());
            if(shenghuo != null){
            BeanUtils.copyProperties( shenghuo , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShenghuoId(shenghuo.getId());
            }
            //级联表 乡村志愿者
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(shenghuoOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody ShenghuoOrderEntity shenghuoOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shenghuoOrder:{}",this.getClass().getName(),shenghuoOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("乡村志愿者".equals(role))
            shenghuoOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        shenghuoOrder.setCreateTime(new Date());
        shenghuoOrder.setInsertTime(new Date());
        shenghuoOrderService.insert(shenghuoOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShenghuoOrderEntity shenghuoOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shenghuoOrder:{}",this.getClass().getName(),shenghuoOrder.toString());
        ShenghuoOrderEntity oldShenghuoOrderEntity = shenghuoOrderService.selectById(shenghuoOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("乡村志愿者".equals(role))
//            shenghuoOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            shenghuoOrderService.updateById(shenghuoOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShenghuoOrderEntity> oldShenghuoOrderList =shenghuoOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shenghuoOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<ShenghuoOrderEntity> shenghuoOrderList = new ArrayList<>();//上传的东西
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
                            ShenghuoOrderEntity shenghuoOrderEntity = new ShenghuoOrderEntity();
//                            shenghuoOrderEntity.setShenghuoOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            shenghuoOrderEntity.setShenghuoId(Integer.valueOf(data.get(0)));   //生活照料 要改的
//                            shenghuoOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shenghuoOrderEntity.setShenghuoOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            shenghuoOrderEntity.setInsertTime(date);//时间
//                            shenghuoOrderEntity.setCreateTime(date);//时间
                            shenghuoOrderList.add(shenghuoOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("shenghuoOrderUuidNumber")){
                                    List<String> shenghuoOrderUuidNumber = seachFields.get("shenghuoOrderUuidNumber");
                                    shenghuoOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shenghuoOrderUuidNumber = new ArrayList<>();
                                    shenghuoOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shenghuoOrderUuidNumber",shenghuoOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<ShenghuoOrderEntity> shenghuoOrderEntities_shenghuoOrderUuidNumber = shenghuoOrderService.selectList(new EntityWrapper<ShenghuoOrderEntity>().in("shenghuo_order_uuid_number", seachFields.get("shenghuoOrderUuidNumber")));
                        if(shenghuoOrderEntities_shenghuoOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShenghuoOrderEntity s:shenghuoOrderEntities_shenghuoOrderUuidNumber){
                                repeatFields.add(s.getShenghuoOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shenghuoOrderService.insertBatch(shenghuoOrderList);
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
        PageUtils page = shenghuoOrderService.queryPage(params);

        //字典表数据转换
        List<ShenghuoOrderView> list =(List<ShenghuoOrderView>)page.getList();
        for(ShenghuoOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShenghuoOrderEntity shenghuoOrder = shenghuoOrderService.selectById(id);
            if(shenghuoOrder !=null){


                //entity转view
                ShenghuoOrderView view = new ShenghuoOrderView();
                BeanUtils.copyProperties( shenghuoOrder , view );//把实体数据重构到view中

                //级联表
                    ShenghuoEntity shenghuo = shenghuoService.selectById(shenghuoOrder.getShenghuoId());
                if(shenghuo != null){
                    BeanUtils.copyProperties( shenghuo , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShenghuoId(shenghuo.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shenghuoOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
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
    public R add(@RequestBody ShenghuoOrderEntity shenghuoOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shenghuoOrder:{}",this.getClass().getName(),shenghuoOrder.toString());
            ShenghuoEntity shenghuoEntity = shenghuoService.selectById(shenghuoOrder.getShenghuoId());
            if(shenghuoEntity == null){
                return R.error(511,"查不到该生活照料");
            }
            // Double shenghuoNewMoney = shenghuoEntity.getShenghuoNewMoney();

            if(false){
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"乡村志愿者不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"乡村志愿者金额不能为空");
            shenghuoOrder.setShenghuoOrderTypes(101); //设置订单状态为已接单
            shenghuoOrder.setYonghuId(userId); //设置订单支付人id
            shenghuoOrder.setShenghuoOrderUuidNumber(String.valueOf(new Date().getTime()));
            shenghuoOrder.setInsertTime(new Date());
            shenghuoOrder.setCreateTime(new Date());
                shenghuoOrderService.insert(shenghuoOrder);//新增订单
            //更新第一注册表
            yonghuService.updateById(yonghuEntity);

            LaorenEntity laorenEntity = laorenService.selectById(shenghuoEntity.getLaorenId());
            laorenService.updateById(laorenEntity);

            return R.ok();
    }


    /**
    * 取消接单
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            ShenghuoOrderEntity shenghuoOrder = shenghuoOrderService.selectById(id);//当前表service
            Integer shenghuoId = shenghuoOrder.getShenghuoId();
            if(shenghuoId == null)
                return R.error(511,"查不到该生活照料");
            ShenghuoEntity shenghuoEntity = shenghuoService.selectById(shenghuoId);
            if(shenghuoEntity == null)
                return R.error(511,"查不到该生活照料");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

                //计算金额
                //计算所获得积分
                Double buyJifen = 0.0;





            shenghuoOrder.setShenghuoOrderTypes(102);//设置订单状态为已取消接单
            shenghuoOrderService.updateById(shenghuoOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            shenghuoService.updateById(shenghuoEntity);//更新订单中生活照料的信息

            return R.ok();
    }

    /**
     * 上门
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ShenghuoOrderEntity  shenghuoOrderEntity = shenghuoOrderService.selectById(id);
        shenghuoOrderEntity.setShenghuoOrderTypes(103);//设置订单状态为已上门
        shenghuoOrderService.updateById( shenghuoOrderEntity);

        return R.ok();
    }


}

