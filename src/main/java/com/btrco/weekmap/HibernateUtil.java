package com.btrco.weekmap;

import com.btrco.weekmap.model.Event;
import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        Configuration cfg = new Configuration().configure();

        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Event.class);
        cfg.addAnnotatedClass(MapPoint.class);
//        cfg.addAttributeConverter(LocalDateTimeConverter.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        sessionFactory = cfg.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
