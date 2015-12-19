package com.btrco.weekmap.dao;

import com.btrco.weekmap.HibernateUtil;
import com.btrco.weekmap.model.MapPoint;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MapPointDAOImpl implements MapPointDAO {

    private Session currentSession;
    private Transaction currentTransaction;

    public MapPointDAOImpl() {
    }

    public void create(MapPoint mapPoint) {
        openCurrentSessionWithTransaction();
        getCurrentSession().save(mapPoint);
        closeCurrentSessionWithTransaction();
    }

    public MapPoint findById(int id) {
        openCurrentSession();
        MapPoint mapPoint =  getCurrentSession().get(MapPoint.class, id);
        closeCurrentSession();
        return mapPoint;
    }

    public List<MapPoint> findAll() {
        openCurrentSession();
        List<MapPoint> mapPoints = getCurrentSession().createQuery("from MapPoint").list();
        closeCurrentSession();
        return mapPoints;
    }

    public void update(MapPoint mapPoint) {
        openCurrentSessionWithTransaction();
        getCurrentSession().update(mapPoint);
        closeCurrentSessionWithTransaction();
    }

    public void delete(MapPoint mapPoint) {
        openCurrentSessionWithTransaction();
        getCurrentSession().delete(mapPoint);
        closeCurrentSessionWithTransaction();
    }

    public Session openCurrentSession(){
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = HibernateUtil.getSessionFactory().openSession();
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
}
