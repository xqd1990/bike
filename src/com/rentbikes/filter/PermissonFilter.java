package com.rentbikes.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rentbikes.dao.basicInfo.MSPhaseDao;
import com.rentbikes.model.MSPhase;
import com.rentbikes.model.SYUser;

public class PermissonFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		uri = uri.substring(10);
		SYUser user = (SYUser) req.getSession().getAttribute("user");
		
		BeanFactory facory = new ClassPathXmlApplicationContext("springmvc.xml");
		MSPhaseDao phaseDao = facory.getBean("MSPhaseDao", MSPhaseDao.class);
		List<MSPhase> phaseList = phaseDao.listAllByRoleId(user.getRole_id());
		
		boolean flag = false;
		for(MSPhase phase : phaseList){
			if(uri.startsWith(phase.getUrl())){
				flag = true;
				break;
			}
		}
		if(flag){
			chain.doFilter(request, response);
		}else{
			resp.sendRedirect(req.getContextPath() + "/prompt.jsp");
		}
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
