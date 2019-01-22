package cn.gzsxt.transport.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.gzsxt.transport.annotation.TokenForm;

/**
 *  防重提交拦截器的实现
 * @author ranger
 *
 */
public class TokenFormInterceptor implements HandlerInterceptor {
	
	private static final Logger logger =LogManager.getLogger(TokenFormInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		//表单传递过来的Token
		String requestToken = request.getParameter("token");
		String invoke = request.getParameter("token.invoke");
		//1.获得调用方法的注解
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		//2.获得调用方法的动态方法类对象
		Method method = handlerMethod.getMethod();
		TokenForm tokenForm = method.getDeclaredAnnotation(TokenForm.class);
		if(tokenForm!=null) {
			//判断如果是一个创建Token的方法，创建一个Token方法session里面
			if (tokenForm.create()==true) {
				//创建一个UUID，随机的唯一字符串
				String token = UUID.randomUUID().toString();
				session.setAttribute("token",token);
				logger.debug("创建令牌："+token);
				//允许访问
				return true;
			}
			
			if (tokenForm.remove()==true) {
				String sessionToken = (String) session.getAttribute("token");
				//判断是否相同
				if(sessionToken.equals(requestToken)) {
					session.removeAttribute("token");
					//允许访问
					return true;
				}
				
			}
			//跳转到页面指定的路径
			response.sendRedirect(request.getContextPath()+invoke);
			return false;
		}else {
			//如果不需要防重提的请求，直接跳过
			return true;
		}
		
	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
