package ch.bfh.bti7081.s2013.yellow.batch;

public interface TriggerMBean {
    public void setLoggingLevel(int level);
    public long getUptime();
    public void createNotifications(int interval);
  }