package com.rentbikes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rentbikes.dao.basicInfo.SYUserDao;
import com.rentbikes.model.SYUser;

/*
 * 接收客户端登录请求
 */
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login_name = request.getParameter("login_name");
		String password = request.getParameter("password");
		SYUser user = new SYUser();
		user.setLogin_name(login_name);
		user.setPassword(password);
		
		BeanFactory factory = new ClassPathXmlApplicationContext("springmvc.xml");
		SYUserDao userDao = factory.getBean("SYUserDao", SYUserDao.class);
		SYUser dUser = userDao.getForLogin(user);
		if(dUser == null){
			request.setAttribute("prompt", "账户名或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);;
		}
		else{
			request.getSession().setAttribute("user", dUser);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
