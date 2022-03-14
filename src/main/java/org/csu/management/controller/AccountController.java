package org.csu.management.controller;


import org.csu.management.domain.User;
import org.csu.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/viewAccounts")
    public String viewaccounts(Model model){
        List<User> userList = userService.searchUserList();
        model.addAttribute("userList",userList);
        return "account/Accounts";
    }

    @GetMapping("/manageAccount")
    public String manageAccount(String username,Model model){

        User user = userService.findUserByUsername(username);
        model.addAttribute("user",user);
        return "account/ManageAccount";

    }

    @PostMapping("/saveAccount")
    public String saveAccount(String password, String repeatpwd, Model model, HttpServletRequest request, User user){
        String message = null;
        boolean result = userService.updateUserByUsername(user);
        if(result == true){
            request.getSession().setAttribute("user",user);
            return viewaccounts(model);
        }
        else{
            message = "申请失败，请重新输入！";
            request.getSession().setAttribute("message",message);
            return "/account/ManageAccount";
        }
    }


}
