package ch.bfh.bti7081.s2013.yellow.util;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringHelper {

	private ApplicationContext context;
	public SpringHelper(ServletContext servletContext) {
        /*ServletContext servletContext = 
                ((WebApplicationContext) application.getContext())
                .getHttpSession().getServletContext();*/
		context = WebApplicationContextUtils.
				getRequiredWebApplicationContext(servletContext);
	}

	public Object getBean(final String beanRef) {
		return context.getBean(beanRef);
	}
}