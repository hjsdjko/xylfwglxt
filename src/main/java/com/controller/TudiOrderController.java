
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
 * 土地承包
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tudiOrder")
public class TudiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(TudiOrderController.class);

    private static final String TABLE_NAME = "tudiOrder";

    @Autowired
    private TudiOrderService tudiOrderService;


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
    private TudiService tudiService;//土地
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
        PageUtils page = tudiOrderService.queryPage(params);

        //字典表数据转换
        List<TudiOrderView> list =(List<TudiOrderView>)page.getList();
        for(TudiOrderView c:list){
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
        TudiOrderEntity tudiOrder = tudiOrderService.selectById(id);
        if(tudiOrder !=null){
            //entity转view
            TudiOrderView view = new TudiOrderView();
            BeanUtils.copyProperties( tudiOrder , view );//把实体数据重构到view中
            //级联表 土地
            //级联表
            TudiEntity tudi = tudiService.selectById(tudiOrder.getTudiId());
            if(tudi != null){
            BeanUtils.copyProperties( tudi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setTudiId(tudi.getId());
            }
            //级联表 乡村志愿者
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(tudiOrder.getYonghuId());
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
    public R save(@RequestBody TudiOrderEntity tudiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tudiOrder:{}",this.getClass().getName(),tudiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("乡村志愿者".equals(role))
            tudiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        tudiOrder.setCreateTime(new Date());
        tudiOrder.setInsertTime(new Date());
        tudiOrderService.insert(tudiOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TudiOrderEntity tudiOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,tudiOrder:{}",this.getClass().getName(),tudiOrder.toString());
        TudiOrderEntity oldTudiOrderEntity = tudiOrderService.selectById(tudiOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("乡村志愿者".equals(role))
//            tudiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            tudiOrderService.updateById(tudiOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<TudiOrderEntity> oldTudiOrderList =tudiOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        tudiOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<TudiOrderEntity> tudiOrderList = new ArrayList<>();//上传的东西
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
                            TudiOrderEntity tudiOrderEntity = new TudiOrderEntity();
//                            tudiOrderEntity.setTudiOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            tudiOrderEntity.setTudiId(Integer.valueOf(data.get(0)));   //土地 要改的
//                            tudiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            tudiOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //亩 要改的
//                            tudiOrderEntity.setTudiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            tudiOrderEntity.setInsertTime(date);//时间
//                            tudiOrderEntity.setCreateTime(date);//时间
                            tudiOrderList.add(tudiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("tudiOrderUuidNumber")){
                                    List<String> tudiOrderUuidNumber = seachFields.get("tudiOrderUuidNumber");
                                    tudiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tudiOrderUuidNumber = new ArrayList<>();
                                    tudiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tudiOrderUuidNumber",tudiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<TudiOrderEntity> tudiOrderEntities_tudiOrderUuidNumber = tudiOrderService.selectList(new EntityWrapper<TudiOrderEntity>().in("tudi_order_uuid_number", seachFields.get("tudiOrderUuidNumber")));
                        if(tudiOrderEntities_tudiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TudiOrderEntity s:tudiOrderEntities_tudiOrderUuidNumber){
                                repeatFields.add(s.getTudiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tudiOrderService.insertBatch(tudiOrderList);
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
        PageUtils page = tudiOrderService.queryPage(params);

        //字典表数据转换
        List<TudiOrderView> list =(List<TudiOrderView>)page.getList();
        for(TudiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TudiOrderEntity tudiOrder = tudiOrderService.selectById(id);
            if(tudiOrder !=null){


                //entity转view
                TudiOrderView view = new TudiOrderView();
                BeanUtils.copyProperties( tudiOrder , view );//把实体数据重构到view中

                //级联表
                    TudiEntity tudi = tudiService.selectById(tudiOrder.getTudiId());
                if(tudi != null){
                    BeanUtils.copyProperties( tudi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTudiId(tudi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(tudiOrder.getYonghuId());
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
    public R add(@RequestBody TudiOrderEntity tudiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tudiOrder:{}",this.getClass().getName(),tudiOrder.toString());
            TudiEntity tudiEntity = tudiService.selectById(tudiOrder.getTudiId());
            if(tudiEntity == null){
                return R.error(511,"查不到该土地");
            }
            // Double tudiNewMoney = tudiEntity.getTudiNewMoney();

            if(false){
            }
            else if(tudiEntity.getTudiNewMoney() == null){
                return R.error(511,"价格/年不能为空");
            }
            else if((tudiEntity.getTudiKucunNumber() -tudiOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"乡村志愿者不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"乡村志愿者金额不能为空");
            double balance = yonghuEntity.getNewMoney() - tudiEntity.getTudiNewMoney()*tudiOrder.getBuyNumber();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            tudiOrder.setTudiOrderTypes(101); //设置订单状态为已申请承包
//            tudiOrder.setTudiOrderTruePrice(tudiEntity.getTudiNewMoney()*tudiOrder.getBuyNumber()); //设置实付价格
            tudiOrder.setYonghuId(userId); //设置订单支付人id
            tudiOrder.setTudiOrderUuidNumber(String.valueOf(new Date().getTime()));
            tudiOrder.setInsertTime(new Date());
            tudiOrder.setCreateTime(new Date());
                tudiEntity.setTudiKucunNumber( tudiEntity.getTudiKucunNumber() -tudiOrder.getBuyNumber());
                tudiService.updateById(tudiEntity);
                tudiOrderService.insert(tudiOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);

            LaorenEntity laorenEntity = laorenService.selectById(tudiEntity.getLaorenId());
//            laorenEntity.setNewMoney(laorenEntity.getNewMoney()+tudiOrder.getTudiOrderTruePrice());//动态计算金额
            laorenService.updateById(laorenEntity);

            return R.ok();
    }


    /**
    * 取消申请
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            TudiOrderEntity tudiOrder = tudiOrderService.selectById(id);//当前表service
            Integer buyNumber = tudiOrder.getBuyNumber();
            Integer tudiId = tudiOrder.getTudiId();
            if(tudiId == null)
                return R.error(511,"查不到该土地");
            TudiEntity tudiEntity = tudiService.selectById(tudiId);
            if(tudiEntity == null)
                return R.error(511,"查不到该土地");
            Double tudiNewMoney = tudiEntity.getTudiNewMoney();
            if(tudiNewMoney == null)
                return R.error(511,"土地价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

                //计算金额
                Double money = tudiEntity.getTudiNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额



            tudiEntity.setTudiKucunNumber(tudiEntity.getTudiKucunNumber() + buyNumber);


            tudiOrder.setTudiOrderTypes(102);//设置订单状态为已取消申请
            tudiOrderService.updateById(tudiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            tudiService.updateById(tudiEntity);//更新订单中土地的信息

            return R.ok();
    }

    /**
     * 同意承包
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        TudiOrderEntity  tudiOrderEntity = tudiOrderService.selectById(id);
        tudiOrderEntity.setTudiOrderTypes(103);//设置订单状态为已同意承包
        tudiOrderService.updateById( tudiOrderEntity);

        return R.ok();
    }


}

