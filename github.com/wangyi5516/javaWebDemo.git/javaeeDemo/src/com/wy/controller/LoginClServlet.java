package com.wy.controller;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.wy.entity.UserEntity;
import com.wy.model.DBHelper;
import com.wy.model.ResultSetMapper;
import com.wy.model.User;

/**
 * Servlet implementation class LoginClServlet
 */
@WebServlet("/LoginCl")
public class LoginClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginClServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		MultipartRequest ms = new MultipartRequest(request, "D:\\img",104857600,"utf-8");
		
		File  file = ms.getFile("file");		
		
	
		
		String account = ms.getParameter("account");
		String password = ms.getParameter("password");	
		
		Logger.getLogger("").info(account);
		
		if(checkPassword(account,password)){//user.isExist()){//"admin".equals(account) && "123456".equals(password)
			response.sendRedirect("welcome");
		}else{
			response.sendRedirect("LoginError");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean checkPassword(String account, String password){
		
		if(account == null || password == null)
			return false;
		
		String cmd = "select * from userInfo where userId='"+account+"'";
		DBHelper db = new DBHelper();
		try {
			ResultSet rs = db.Query(cmd);
			
			ResultSetMapper<UserEntity> rsm = new ResultSetMapper<UserEntity>();
			List<UserEntity> userList = rsm.mapRersultSetToObject(rs, UserEntity.class);
			if(userList != null && !userList.isEmpty()){
				UserEntity user = userList.get(0);
				Logger.getLogger("wy").info("user:"+user.toString());
				if(password.equals(user.getPassword())){
					return true;
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
