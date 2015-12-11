package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.MapPoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MapPointDAOImpl implements MapPointDAO {

    private Session currentSession;
    private Transaction currentTransaction;

    public MapPointDAOImpl() {
    }

    private SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(MapPoint.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session openCurrentSession(){
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession(){
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void create(MapPoint mapPoint) {
        getCurrentSession().save(mapPoint);
    }

    public MapPoint findById(int id) {
        return getCurrentSession().get(MapPoint.class, id);
    }

    public List<MapPoint> findAll() {
        List<MapPoint> mapPoints = getCurrentSession().createQuery("from MapPoint").list();
        return mapPoints;
    }

    public void update(MapPoint mapPoint) {
        getCurrentSession().update(mapPoint);
    }

    public void delete(MapPoint mapPoint) {
        getCurrentSession().delete(mapPoint);
    }
}
