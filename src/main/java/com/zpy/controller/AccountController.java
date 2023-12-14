package com.zpy.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zpy.pojo.Customer;
import com.zpy.pojo.User;
import com.zpy.service.AccountService;
import com.zpy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Reader;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    //跳转到登录页面
    @GetMapping("/toLogin")
    public String toLogin(){
        return "index";
    }
    //跳转到注册页面
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    //跳转到主页面
    @RequestMapping("/toDashboard")
    public String toDashboard(){
        return "foodMainMenu";
    }


    //登录
    @RequestMapping("/login")
    public String login(String userName, String userPwd, Model model, HttpSession session) {

        if (userName.equals("admin")){
            boolean login = accountService.login(userName, userPwd);
            if (login) {
                QueryWrapper<User>qw=new QueryWrapper<>();
                User one = accountService.getOne(qw);
                session.setAttribute("currentUser", userName);
                session.setAttribute("password",userPwd);
                session.setAttribute("email",one.getEmail());
                session.setAttribute("image",one.getImage());
                return "foodMainMenu";
            } else {
                model.addAttribute("msg", "用户名或密码错误！");
                return "index";
            }
        }else {
            boolean login = customerService.login(userName, userPwd);
            if (login){
                QueryWrapper<Customer>qw=new QueryWrapper<>();
                qw.eq("customer_name",userName);
                Customer one = customerService.getOne(qw);
                session.setAttribute("currentUser",userName);
                session.setAttribute("userId",one.getId());
                session.setAttribute("password",userPwd);
                session.setAttribute("image",one.getCimage());
                session.setAttribute("email",one.getEmail());
                return "foodMainMenu1";
            }else {
                model.addAttribute("msg", "用户名或密码错误！");
                return "index";
            }
        }
    }

    //注册
    @RequestMapping("/register")
    public String register(String userName, String userPwd,String confirmPwd, Model model) {

        if (!userPwd.equals(confirmPwd)){
            model.addAttribute("msg", "输入密码不一致");
            return "register";
        }else {
            Customer customer = new Customer();
            customer.setCustomerName(userName);
            String s = DigestUtil.md5Hex(userPwd);
            customer.setPassword(s);
            customerService.save(customer);
            return "index";
        }

    }

    //跳转到修改密码的页面
    @RequestMapping("pwd")
    public String preUpdate() {
        return "modify";
    }

    //修改密码
    @PostMapping("pwdUser")
    public String updatePwd(String userPwd, String newPwd, Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        boolean login = accountService.login(currentUser, userPwd);
        if (login) {
            User user = new User();
            user.setUsername(currentUser);
            String newPassword = DigestUtil.md5Hex(newPwd);
            user.setPassword(newPassword);
            QueryWrapper<User>qw=new QueryWrapper<>();
            qw.eq("username",user.getUsername());
            boolean b = accountService.update(user,qw);
            if (b) {
                return "index";
            } else {
                model.addAttribute("loginFail", "修改密码失败");
            }
        } else {
            model.addAttribute("loginFail", "用户验证失败");
        }
        return "modify";
    }

    //登出
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }


    //跳转到统计销售量的页面
    @RequestMapping("count")
    public String count(){
        return "chart_count";
    }
    //跳转到统计销售额的页面
    @RequestMapping("total")
    public String count1(){
        return "chart_total";
    }

    //跳转到修改个人信息的页面
    @RequestMapping("profile")
    public String profile(HttpServletRequest request,Model model){
        String currentUser = (String) request.getSession().getAttribute("currentUser");
        String password =(String) request.getSession().getAttribute("password");

        if (currentUser.equals("admin")){
            QueryWrapper<User>qw=new QueryWrapper<>();
            qw.eq("username",currentUser);
            User one = accountService.getOne(qw);
            one.setPassword(password);//为了在个人简介页面显示的是没加密的密码
            model.addAttribute("user",one);
            return "profile-admin";
        }else {
            QueryWrapper<Customer>qw=new QueryWrapper<>();
            qw.eq("customer_name",currentUser);
            Customer one = customerService.getOne(qw);
            one.setPassword(password);
            model.addAttribute("user",one);
            return "profile-customer";
        }
    }

    //修改管理员个人信息
    @RequestMapping("updateAdminProfile")
    public String updateProfile(User user){
        //修改数据库
        String s = DigestUtil.md5Hex(user.getPassword());
        user.setPassword(s);
        boolean b = accountService.updateById(user);
        return "index";
    }

    @RequestMapping("updateCustomerProfile")
    public String updateReaderProfile(Customer customer){
        String s = DigestUtil.md5Hex(customer.getPassword());
        customer.setPassword(s);
        boolean b = customerService.updateById(customer);
        return "index";
    }

}
