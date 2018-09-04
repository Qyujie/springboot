package com.how2java.springboot.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.how2java.springboot.mapper.UserInformationMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.User;
import com.how2java.springboot.pojo.UserInformation;

@Controller
public class UserInformationController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserInformationMapper userInformationMapper;

	@RequestMapping("/userInformation")
	public String userInformation(Model m, HttpServletRequest req) {
		
		if (req.getSession().getAttribute("id") != null) {
			
			int id = (int) req.getSession().getAttribute("id");
			UserInformation userinformation = userInformationMapper.getUserInformation(id);
			User user = userMapper.getUserName(id);

			// 传递name
			m.addAttribute("name", user.getName());

			// 传递headPortrait
			if (userinformation.getHead_portrait() != null)
				m.addAttribute("headPortrait", userinformation.getHead_portrait());

			// 传递birthday
			if (userinformation.getBirthday() != null) {
				Calendar calendar = Calendar.getInstance();// 日历对象
				calendar.setTime(userinformation.getBirthday());// 设置当前日期
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;// 以0开始
				int day = calendar.get(Calendar.DATE);
				m.addAttribute("birthdayYear", year);
				m.addAttribute("birthdayMonth", month);
				m.addAttribute("birthdayDay", day);
			}

			// 传递Sex
			if (userinformation.getSex() != null)
				m.addAttribute("sex", userinformation.getSex());

			// 传递Phone
			if (userinformation.getPhone() != null)
				m.addAttribute("phone", userinformation.getPhone());

			// 传递Prefecture(地区)
			if (userinformation.getPrefecture() != null)
				m.addAttribute("prefecture", userinformation.getPrefecture());

			// 传递Introduce(简介)
			if (userinformation.getIntroduce() != null)
				m.addAttribute("introduce", userinformation.getIntroduce());

			// 传递Address(地址)
			if (userinformation.getAddress() != null)
				m.addAttribute("address", userinformation.getAddress());

			// 传递Real_name(真实姓名)
			if (userinformation.getReal_name() != null)
				m.addAttribute("realName", userinformation.getReal_name());

			// 传递Id_card(身份证)
			if (userinformation.getId_card() != null)
				m.addAttribute("idCard", userinformation.getId_card());
		}

		return "userInformation";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model m) {
		try {
			int id = (int) req.getSession().getAttribute("id");
			
			final String uplaodPath = "D:/uploadFiles/";
			final String idPath = id + "/";
			String fileName = System.currentTimeMillis() + file.getOriginalFilename();
			String filePath = idPath + fileName;
			String destFileName = uplaodPath + filePath;
			File destFile = new File(destFileName);
			destFile.getParentFile().mkdirs();
			file.transferTo(destFile);
			
			
			String oldfilePath = userInformationMapper.getUserInformation(id).getHead_portrait();
			File oldFile = new File(uplaodPath+oldfilePath);
			oldFile.delete();
			
			
			UserInformation userinformation = new UserInformation();
			userinformation.setHead_portrait(filePath);
			userinformation.setId(id);
			userInformationMapper.updateHeadPortrait(userinformation);

			return filePath;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "上传失败," + e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return "上传失败," + e.getMessage();
		}
	}

	@RequestMapping("/addUserInformation")
	@ResponseBody
	public String addUserInformation(HttpServletRequest req, UserInformation c) throws Exception {
		int id = (int) req.getSession().getAttribute("id");
		c.setId(id);
		userInformationMapper.updateUserInformation(c);
		User user = new User();
		user.setId(id);
		user.setName(c.getName());
		User checkResult = userMapper.checkName(user);
		if(checkResult==null || checkResult.getId()==id){
			userMapper.update(user);
			return "修改成功";
		}
		else{
			return "已注册";
		}
		
	}
}
