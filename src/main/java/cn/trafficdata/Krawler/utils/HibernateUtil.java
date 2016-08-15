package cn.trafficdata.Krawler.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kinglf on 2016/8/15.
 */
public class HibernateUtil {
    private static Logger logger= LoggerFactory.getLogger(HibernateUtil.class);
    private final static SessionFactory FACTORY = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        Configuration cfg=new Configuration().configure();
        ServiceRegistry serviceRegistry=new ServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .buildServiceRegistry();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        return factory;
    }
    public static SessionFactory getSessionFactory(){
        return FACTORY;
    }
    public static Session getSession(){
        return FACTORY.openSession();
    }
    public void close(Session session){
        if(session!=null)
            session.close();
    }
}
