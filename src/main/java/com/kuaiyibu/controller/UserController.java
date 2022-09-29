package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/*跨域问题加的注解*/
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user/users")
public class UserController {

    @Autowired
    private UserService userService;


    /*用户登录状态查询*/
    @GetMapping(value = "/check" , produces = "text/html; charset=utf-8")
    public String check(HttpSession session){
        if(session.getAttribute("user")!=null){
            User user= (User) session.getAttribute("user");
            return user.getTitleName();
        }
        return null;
    }

    /*用户的登录检查*/
    @GetMapping("/login")
    public Boolean checkLogin(User user,HttpSession session) {
            User user1 = userService.checkLogin(user);
            if (user1!=null){
                session.setAttribute("user",user1);
                return true;
            }
        return false;
    }

    @GetMapping("/quit")
    public Boolean quit(HttpSession session){
        session.setAttribute("user",null);
        return true;
    }

    /*用户注册和修改时对用户名进行限制不重复*/
    /*@GetMapping("/checkuserName")
    public Boolean checkuserName(@RequestParam String userName,HttpSession session){
        User user= (User) session.getAttribute("user");
        *//*查到数据不为1时返回false*//*
        System.out.println(userName);
        if(userService.checkuserName(userName)>0){
            return true;
        }
        return false;
    }*/

    /*session有值时给用户信息*/
    @GetMapping("/getuser")
    public User getuser(HttpSession session){
        User user= (User) session.getAttribute("user");
        return user;
    }

    /*接收前端的用户信息完成注册功能，用户增加*/
    @PostMapping("/add")
    public Boolean addUser(@RequestBody User user){
        /*添加成功返回1*/
        if(userService.checkuserName(user.getUserName())==null){
            userService.addUser(user);
            return true;
        }
        return false;
    }

    /*模糊查询出所有的结果*/
    /*@PostMapping("/dySelect")
    public List<User> dySelect(@RequestBody User user){
        return userService.dySelect(user);
    }*/

    /*用户删除操作，下面路径delete可以不加，后面的{uid需要}，参数上的注解表示在路径上获取数据*/
    /*@DeleteMapping({"/{uid}"})
    public Boolean deleteUser(@PathVariable Integer uid){
        userService.deleteUser(uid);
        *//*删除成功返回1*//*
        System.out.println("删除成功"+uid);
        return true;
    }*/

    /*用户信息的更新*/
    @PutMapping
    public Boolean updateUser(@RequestBody User user,HttpSession session){
        User user1= (User) session.getAttribute("user");
        if(user.getUserName().equals(user1.getUserName())){
            session.setAttribute("user",user);
            userService.updateUser(user);
            return true;
        }
         if(userService.checkuserName(user.getUserName())==null){
            session.setAttribute("user",user);
            userService.updateUser(user);
            return true;
        }
        //System.out.println("更新成功"+user);
        return false;
    }



    /*用户密码的更新*/
    @PutMapping("/changepassword")
    public Boolean updatepassword(@RequestBody User user,HttpSession session){
        User user1= (User) session.getAttribute("user");
        userService.updateUser(user);
        return true;
    }

}
