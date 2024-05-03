
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
 * 医疗人员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yiliao")
public class YiliaoController {
    private static final Logger logger = LoggerFactory.getLogger(YiliaoController.class);

    private static final String TABLE_NAME = "yiliao";

    @Autowired
    private YiliaoService yiliaoService;


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
    private TudiOrderService tudiOrderService;//土地承包
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
        params.put("yiliaoDeleteStart",1);params.put("yiliaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yiliaoService.queryPage(params);

        //字典表数据转换
        List<YiliaoView> list =(List<YiliaoView>)page.getList();
        for(YiliaoView c:list){
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
        YiliaoEntity yiliao = yiliaoService.selectById(id);
        if(yiliao !=null){
            //entity转view
            YiliaoView view = new YiliaoView();
            BeanUtils.copyProperties( yiliao , view );//把实体数据重构到view中
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
    public R save(@RequestBody YiliaoEntity yiliao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yiliao:{}",this.getClass().getName(),yiliao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YiliaoEntity> queryWrapper = new EntityWrapper<YiliaoEntity>()
            .eq("username", yiliao.getUsername())
            .or()
            .eq("yiliao_phone", yiliao.getYiliaoPhone())
            .eq("yiliao_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YiliaoEntity yiliaoEntity = yiliaoService.selectOne(queryWrapper);
        if(yiliaoEntity==null){
            yiliao.setYiliaoDelete(1);
            yiliao.setCreateTime(new Date());
            yiliao.setPassword("123456");
            yiliaoService.insert(yiliao);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YiliaoEntity yiliao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yiliao:{}",this.getClass().getName(),yiliao.toString());
        YiliaoEntity oldYiliaoEntity = yiliaoService.selectById(yiliao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            yiliaoService.updateById(yiliao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YiliaoEntity> oldYiliaoList =yiliaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YiliaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YiliaoEntity yiliaoEntity = new YiliaoEntity();
            yiliaoEntity.setId(id);
            yiliaoEntity.setYiliaoDelete(2);
            list.add(yiliaoEntity);
        }
        if(list != null && list.size() >0){
            yiliaoService.updateBatchById(list);
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
            List<YiliaoEntity> yiliaoList = new ArrayList<>();//上传的东西
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
                            YiliaoEntity yiliaoEntity = new YiliaoEntity();
//                            yiliaoEntity.setUsername(data.get(0));                    //账户 要改的
//                            //yiliaoEntity.setPassword("123456");//密码
//                            yiliaoEntity.setYiliaoName(data.get(0));                    //医疗人员名称 要改的
//                            yiliaoEntity.setYiliaoPhone(data.get(0));                    //联系方式 要改的
//                            yiliaoEntity.setYiliaoEmail(data.get(0));                    //邮箱 要改的
//                            yiliaoEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            yiliaoEntity.setYiliaoContent("");//详情和图片
//                            yiliaoEntity.setYiliaoDelete(1);//逻辑删除字段
//                            yiliaoEntity.setCreateTime(date);//时间
                            yiliaoList.add(yiliaoEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //联系方式
                                if(seachFields.containsKey("yiliaoPhone")){
                                    List<String> yiliaoPhone = seachFields.get("yiliaoPhone");
                                    yiliaoPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> yiliaoPhone = new ArrayList<>();
                                    yiliaoPhone.add(data.get(0));//要改的
                                    seachFields.put("yiliaoPhone",yiliaoPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<YiliaoEntity> yiliaoEntities_username = yiliaoService.selectList(new EntityWrapper<YiliaoEntity>().in("username", seachFields.get("username")).eq("yiliao_delete", 1));
                        if(yiliaoEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YiliaoEntity s:yiliaoEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<YiliaoEntity> yiliaoEntities_yiliaoPhone = yiliaoService.selectList(new EntityWrapper<YiliaoEntity>().in("yiliao_phone", seachFields.get("yiliaoPhone")).eq("yiliao_delete", 1));
                        if(yiliaoEntities_yiliaoPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YiliaoEntity s:yiliaoEntities_yiliaoPhone){
                                repeatFields.add(s.getYiliaoPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yiliaoService.insertBatch(yiliaoList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        YiliaoEntity yiliao = yiliaoService.selectOne(new EntityWrapper<YiliaoEntity>().eq("username", username));
        if(yiliao==null || !yiliao.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(yiliao.getYiliaoDelete() != 1)
            return R.error("账户已被删除");
        String token = tokenService.generateToken(yiliao.getId(),username, "yiliao", "医疗人员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","医疗人员");
        r.put("username",yiliao.getYiliaoName());
        r.put("tableName","yiliao");
        r.put("userId",yiliao.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody YiliaoEntity yiliao, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<YiliaoEntity> queryWrapper = new EntityWrapper<YiliaoEntity>()
            .eq("username", yiliao.getUsername())
            .or()
            .eq("yiliao_phone", yiliao.getYiliaoPhone())
            .andNew()
            .eq("yiliao_delete", 1)
            ;
        YiliaoEntity yiliaoEntity = yiliaoService.selectOne(queryWrapper);
        if(yiliaoEntity != null)
            return R.error("账户或者联系方式已经被使用");
        yiliao.setYiliaoDelete(1);
        yiliao.setCreateTime(new Date());
        yiliaoService.insert(yiliao);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        YiliaoEntity yiliao = yiliaoService.selectById(id);
        yiliao.setPassword("123456");
        yiliaoService.updateById(yiliao);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        YiliaoEntity yiliao = yiliaoService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(yiliao.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(yiliao.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        yiliao.setPassword(newPassword);
		yiliaoService.updateById(yiliao);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        YiliaoEntity yiliao = yiliaoService.selectOne(new EntityWrapper<YiliaoEntity>().eq("username", username));
        if(yiliao!=null){
            yiliao.setPassword("123456");
            yiliaoService.updateById(yiliao);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrYiliao(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        YiliaoEntity yiliao = yiliaoService.selectById(id);
        if(yiliao !=null){
            //entity转view
            YiliaoView view = new YiliaoView();
            BeanUtils.copyProperties( yiliao , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yiliaoService.queryPage(params);

        //字典表数据转换
        List<YiliaoView> list =(List<YiliaoView>)page.getList();
        for(YiliaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YiliaoEntity yiliao = yiliaoService.selectById(id);
            if(yiliao !=null){


                //entity转view
                YiliaoView view = new YiliaoView();
                BeanUtils.copyProperties( yiliao , view );//把实体数据重构到view中

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
    public R add(@RequestBody YiliaoEntity yiliao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yiliao:{}",this.getClass().getName(),yiliao.toString());
        Wrapper<YiliaoEntity> queryWrapper = new EntityWrapper<YiliaoEntity>()
            .eq("username", yiliao.getUsername())
            .or()
            .eq("yiliao_phone", yiliao.getYiliaoPhone())
            .andNew()
            .eq("yiliao_delete", 1)
//            .notIn("yiliao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YiliaoEntity yiliaoEntity = yiliaoService.selectOne(queryWrapper);
        if(yiliaoEntity==null){
            yiliao.setYiliaoDelete(1);
            yiliao.setCreateTime(new Date());
            yiliao.setPassword("123456");
        yiliaoService.insert(yiliao);

            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

}

