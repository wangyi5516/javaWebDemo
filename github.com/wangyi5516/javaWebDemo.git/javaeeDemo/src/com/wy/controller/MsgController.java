package com.wy.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.entity.RequestEntity;
import com.wy.entity.ResponseEntity;

@Controller
public class MsgController {
	@RequestMapping(value = "/usr/verifycode", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity sendVerifyCode(@RequestBody RequestEntity rsq) {
		ResponseEntity rsp = new ResponseEntity();
		
//		String account = rsq.getResq().getParameter("account");
//		String password = ms.getParameter("password");	
		
		Logger.getLogger("").info("1111111111111");
//		try {
//			 rsp=userService.sendVerifyCode(rsq);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			rsp.setResCode(ErrorCode.fail);
//			e.printStackTrace();
//		}
		return rsp;
	}
}
