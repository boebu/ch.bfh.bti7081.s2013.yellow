package ch.bfh.bti7081.s2013.yellow.batch;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public final class ContextListener
  implements ServletContextListener {

  public void contextInitialized(ServletContextEvent event) {
    Trigger mbean = new Trigger();
  }

  public void contextDestroyed(ServletContextEvent event) { }

}
