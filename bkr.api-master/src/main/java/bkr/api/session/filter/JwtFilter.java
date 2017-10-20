package bkr.api.session.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import bkr.Audience;
import bkr.base.api.component.MessageHelper;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.Message;
import bkr.base.api.result.ResultCode;
import bkr.base.util.jwt.JwtHelper;

public class JwtFilter implements Filter{

	@Autowired  
	private Audience audience;
	
	@Autowired
	private MessageHelper messageHelper;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,  
                filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
	        HttpServletRequest httpRequest = (HttpServletRequest)request;  
	        String auth = httpRequest.getHeader("Authorization");  
	        if ((auth != null) && (auth.length() > 7))  
	        {  
	            String HeadStr = auth.substring(0, 6).toLowerCase();  
	            if (HeadStr.compareTo("bearer") == 0)  
	            {  
	                  
	                auth = auth.substring(7, auth.length());   
	                if (JwtHelper.parseJWT(auth, audience.getBase64Secret()) != null)  
	                {  
	                    chain.doFilter(request, response);  
	                    return;  
	                }  
	            }  
	        }  
	          
	        HttpServletResponse httpResponse = (HttpServletResponse) response;  
	        httpResponse.setCharacterEncoding("UTF-8");    
	        httpResponse.setContentType("application/json; charset=utf-8");   
	        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
	  
	        ObjectMapper mapper = new ObjectMapper();  
	          
	        httpResponse.getWriter().write(mapper.writeValueAsString(new JsonResult<String>(ResultCode.FAILURE, messageHelper.getMessage(Message.Failure)))); 
	        return; 
	        
	}

	@Override
	public void destroy() {
		
	}

}
