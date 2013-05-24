package ch.bfh.bti7081.s2013.yellow.batch;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;


import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.medication.MedicamentService;
import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.service.notification.NotificationService;
import ch.bfh.bti7081.s2013.yellow.util.SpringHelper;

import com.vaadin.server.VaadinServlet;

import java.util.ArrayList;

public class Trigger implements TriggerMBean {
  private int _logLevel = 1;
  private long _startTime = 0L;

  public Trigger() {
    MBeanServer server = getServer();

    ObjectName name = null;
    try {
      name = new ObjectName("Batches:Name=SystemTrigger,Type=SystemTrigger");
      server.registerMBean(this, name);
    } catch (Exception e) {
      e.printStackTrace();
    }

    _startTime = System.currentTimeMillis();
  }


  private MBeanServer getServer() {
    MBeanServer mbserver = null;

    ArrayList mbservers = MBeanServerFactory.findMBeanServer(null);

    if (mbservers.size() > 0) {
      mbserver = (MBeanServer) mbservers.get(0);
    }

    if (mbserver != null) {
      System.out.println("Found our MBean server");
    } else {
      mbserver = MBeanServerFactory.createMBeanServer();
    } 

    return mbserver;
  }


  // interface method implementations
  public void setLoggingLevel(int level) { _logLevel = level; }
  public long getUptime() { return System.currentTimeMillis() - _startTime; }
  public void createNotifications(int interval) {
	    SpringHelper springHelper = new SpringHelper(VaadinServlet.getCurrent().getServletContext());
		PrescriptionService prescriptionService = (PrescriptionService)springHelper.getBean("prescriptionService");
		NotificationService notificationService = (NotificationService)springHelper.getBean("notificationService");
	for(Prescription prescription : prescriptionService.findByCriteria()) {
		Notification notification = new Notification(null, null);
		notificationService.save(notification);
	}
  }
		
}