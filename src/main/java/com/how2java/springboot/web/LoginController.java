package com.how2java.springboot.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.how2java.springboot.mapper.UserInformationMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.User;
import com.how2java.springboot.pojo.UserInformation;


@Controller
public class LoginController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserInformationMapper userInformationMapper;


	@RequestMapping("/login")
	public String login(Model m) {
		return "login";
	}
	@RequestMapping("/div")
	public String div(Model m) {
		return "div";
	}
	@RequestMapping("/audio")//未实现
	public String audio(Model m) {
		return "audio";
	}
	
	@RequestMapping("/")
	public String login_null(HttpServletRequest req) {
		return "login";
	}
	
	@RequestMapping("/home")
	public String home(Model m) {
		return "home";
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String listUser(User c) throws Exception {
		User result = userMapper.checkName(c);
		if (result != null) {
			return "已注册";
		} else {
			userMapper.save(c);
			User user = userMapper.getUserId(c.getName());
			
			UserInformation userinformation = new UserInformation();
			userinformation.setId(user.getId());
			userInformationMapper.saveUserInformation(userinformation);
			return "registerSuccess";
		}
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(User c) throws Exception {
		userMapper.delete(c.getId());
		return "redirect:login";
	}

	@RequestMapping("/updateUser")
	public String updateUser(User c) throws Exception {
		userMapper.update(c);
		return "redirect:login";
	}

	@RequestMapping("/verificationUser")
	@ResponseBody
	public String verificationUser(User c ,HttpServletRequest req) throws Exception {
		User resultCheck = userMapper.checkName(c);
		if (resultCheck != null) {
			User resultVerification = userMapper.verification(c);
			if (null != resultVerification) {
				req.getSession().setAttribute("id", resultVerification.getId());
				return "loginSuccess";
			} else {
				return "密码错误";
			}
		}
		else{
			return "未注册";
		}
	}
	
	@RequestMapping("/checkName")
	@ResponseBody
	public String checkName(User c) throws Exception {
		User result = userMapper.checkName(c);
		if (result != null) {
			return "已注册";
		} else {
			return "未注册";
		}
	}

}