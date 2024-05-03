
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
 * 老人
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/laoren")
public class LaorenController {
    private static final Logger logger = LoggerFactory.getLogger(LaorenController.class);

    private static final String TABLE_NAME = "laoren";

    @Autowired
    private LaorenService laorenService;


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
        params.put("laorenDeleteStart",1);params.put("laorenDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = laorenService.queryPage(params);

        //字典表数据转换
        List<LaorenView> list =(List<LaorenView>)page.getList();
        for(LaorenView c:list){
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
        LaorenEntity laoren = laorenService.selectById(id);
        if(laoren !=null){
            //entity转view
            LaorenView view = new LaorenView();
            BeanUtils.copyProperties( laoren , view );//把实体数据重构到view中
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
    public R save(@RequestBody LaorenEntity laoren, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,laoren:{}",this.getClass().getName(),laoren.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<LaorenEntity> queryWrapper = new EntityWrapper<LaorenEntity>()
            .eq("username", laoren.getUsername())
            .or()
            .eq("laoren_phone", laoren.getLaorenPhone())
            .eq("laoren_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LaorenEntity laorenEntity = laorenService.selectOne(queryWrapper);
        if(laorenEntity==null){
            laoren.setLaorenDelete(1);
            laoren.setCreateTime(new Date());
            laoren.setPassword("123456");
            laorenService.insert(laoren);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LaorenEntity laoren, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,laoren:{}",this.getClass().getName(),laoren.toString());
        LaorenEntity oldLaorenEntity = laorenService.selectById(laoren.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            laorenService.updateById(laoren);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LaorenEntity> oldLaorenList =laorenService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<LaorenEntity> list = new ArrayList<>();
        for(Integer id:ids){
            LaorenEntity laorenEntity = new LaorenEntity();
            laorenEntity.setId(id);
            laorenEntity.setLaorenDelete(2);
            list.add(laorenEntity);
        }
        if(list != null && list.size() >0){
            laorenService.updateBatchById(list);
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
            List<LaorenEntity> laorenList = new ArrayList<>();//上传的东西
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
                            LaorenEntity laorenEntity = new LaorenEntity();
//                            laorenEntity.setUsername(data.get(0));                    //账户 要改的
//                            //laorenEntity.setPassword("123456");//密码
//                            laorenEntity.setLaorenName(data.get(0));                    //老人名称 要改的
//                            laorenEntity.setLaorenPhone(data.get(0));                    //联系方式 要改的
//                            laorenEntity.setLaorenEmail(data.get(0));                    //邮箱 要改的
//                            laorenEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            laorenEntity.setNewMoney(data.get(0));                    //余额 要改的
//                            laorenEntity.setLaorenContent("");//详情和图片
//                            laorenEntity.setLaorenDelete(1);//逻辑删除字段
//                            laorenEntity.setCreateTime(date);//时间
                            laorenList.add(laorenEntity);


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
                                if(seachFields.containsKey("laorenPhone")){
                                    List<String> laorenPhone = seachFields.get("laorenPhone");
                                    laorenPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> laorenPhone = new ArrayList<>();
                                    laorenPhone.add(data.get(0));//要改的
                                    seachFields.put("laorenPhone",laorenPhone);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<LaorenEntity> laorenEntities_username = laorenService.selectList(new EntityWrapper<LaorenEntity>().in("username", seachFields.get("username")).eq("laoren_delete", 1));
                        if(laorenEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LaorenEntity s:laorenEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<LaorenEntity> laorenEntities_laorenPhone = laorenService.selectList(new EntityWrapper<LaorenEntity>().in("laoren_phone", seachFields.get("laorenPhone")).eq("laoren_delete", 1));
                        if(laorenEntities_laorenPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LaorenEntity s:laorenEntities_laorenPhone){
                                repeatFields.add(s.getLaorenPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        laorenService.insertBatch(laorenList);
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
        LaorenEntity laoren = laorenService.selectOne(new EntityWrapper<LaorenEntity>().eq("username", username));
        if(laoren==null || !laoren.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(laoren.getLaorenDelete() != 1)
            return R.error("账户已被删除");
        String token = tokenService.generateToken(laoren.getId(),username, "laoren", "老人");
        R r = R.ok();
        r.put("token", token);
        r.put("role","老人");
        r.put("username",laoren.getLaorenName());
        r.put("tableName","laoren");
        r.put("userId",laoren.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody LaorenEntity laoren, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<LaorenEntity> queryWrapper = new EntityWrapper<LaorenEntity>()
            .eq("username", laoren.getUsername())
            .or()
            .eq("laoren_phone", laoren.getLaorenPhone())
            .andNew()
            .eq("laoren_delete", 1)
            ;
        LaorenEntity laorenEntity = laorenService.selectOne(queryWrapper);
        if(laorenEntity != null)
            return R.error("账户或者联系方式已经被使用");
        laoren.setNewMoney(0.0);
        laoren.setLaorenDelete(1);
        laoren.setCreateTime(new Date());
        laorenService.insert(laoren);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        LaorenEntity laoren = laorenService.selectById(id);
        laoren.setPassword("123456");
        laorenService.updateById(laoren);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        LaorenEntity laoren = laorenService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(laoren.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(laoren.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        laoren.setPassword(newPassword);
		laorenService.updateById(laoren);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        LaorenEntity laoren = laorenService.selectOne(new EntityWrapper<LaorenEntity>().eq("username", username));
        if(laoren!=null){
            laoren.setPassword("123456");
            laorenService.updateById(laoren);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrLaoren(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        LaorenEntity laoren = laorenService.selectById(id);
        if(laoren !=null){
            //entity转view
            LaorenView view = new LaorenView();
            BeanUtils.copyProperties( laoren , view );//把实体数据重构到view中

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
        PageUtils page = laorenService.queryPage(params);

        //字典表数据转换
        List<LaorenView> list =(List<LaorenView>)page.getList();
        for(LaorenView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LaorenEntity laoren = laorenService.selectById(id);
            if(laoren !=null){


                //entity转view
                LaorenView view = new LaorenView();
                BeanUtils.copyProperties( laoren , view );//把实体数据重构到view中

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
    public R add(@RequestBody LaorenEntity laoren, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,laoren:{}",this.getClass().getName(),laoren.toString());
        Wrapper<LaorenEntity> queryWrapper = new EntityWrapper<LaorenEntity>()
            .eq("username", laoren.getUsername())
            .or()
            .eq("laoren_phone", laoren.getLaorenPhone())
            .andNew()
            .eq("laoren_delete", 1)
//            .notIn("laoren_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LaorenEntity laorenEntity = laorenService.selectOne(queryWrapper);
        if(laorenEntity==null){
            laoren.setLaorenDelete(1);
            laoren.setCreateTime(new Date());
            laoren.setPassword("123456");
        laorenService.insert(laoren);

            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式已经被使用");
        }
    }

}

