package com.btrco.weekmap.dao;

import com.btrco.weekmap.model.MapPoint;
import com.btrco.weekmap.service.SessionService;

import java.util.List;

public class MapPointDAOImpl implements MapPointDAO {

    public static SessionService sessionService;

    public MapPointDAOImpl() {
        sessionService = new SessionService();
    }

    public void create(MapPoint mapPoint) {
        sessionService.getCurrentSession().save(mapPoint);
    }

    public MapPoint findById(int id) {
        return sessionService.getCurrentSession().get(MapPoint.class, id);
    }

    public List<MapPoint> findAll() {
        List<MapPoint> mapPoints = sessionService.getCurrentSession().createQuery("from MapPoint").list();
        return mapPoints;
    }

    public void update(MapPoint mapPoint) {
        sessionService.getCurrentSession().update(mapPoint);
    }

    public void delete(MapPoint mapPoint) {
        sessionService.getCurrentSession().delete(mapPoint);
    }
}
