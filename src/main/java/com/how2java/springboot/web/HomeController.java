package com.how2java.springboot.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.how2java.springboot.mapper.UserInformationMapper;
import com.how2java.springboot.pojo.UserInformation;

@Controller
public class HomeController {

	@Autowired
	UserInformationMapper userInformationMapper;

	@RequestMapping("/home")
	public String home(Model m, HttpServletRequest req) {
		return "home";
	}

	@RequestMapping("/getHeadPortrait")
	@ResponseBody
	public String getHeadPortrait(HttpServletRequest req) {
		if(req.getSession().getAttribute("id")!=null){
			int id = (int) req.getSession().getAttribute("id");
			UserInformation userinformation = userInformationMapper.getUserInformation(id);
			if (userinformation.getHead_portrait() != null)
				return userinformation.getHead_portrait();
			else
				return "nullHeadPortrait";
		}
		else
			return "null";
	}
	
	@RequestMapping("/exit")
	public void exit(HttpServletRequest req) {
		req.getSession().removeAttribute("id");
	}
	
}
