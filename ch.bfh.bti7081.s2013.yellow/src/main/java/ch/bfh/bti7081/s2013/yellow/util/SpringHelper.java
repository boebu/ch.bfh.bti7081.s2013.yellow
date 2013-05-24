package ch.bfh.bti7081.s2013.yellow.util;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author rohdj1
 * This helper is used since @Autowired annotations are not supported within Vaadin objects.
 * With this helper a service from the spring context can be loaded in a vaadin object.
 */
public class SpringHelper {

	private ApplicationContext context;

	/**
	 * Get the ApplicationContext from the ServletContext
	 * @param servletContext ServletContext from the current servlet.
	 */
	public SpringHelper(ServletContext servletContext) {
		context = WebApplicationContextUtils.
				getRequiredWebApplicationContext(servletContext);
	}

	/**
	 * Returns a spring bean by its name
	 * @param beanRef Name of the spring bean
	 * @return Spring bean
	 */
	public Object getBean(final String beanRef) {
		return context.getBean(beanRef);
	}
}