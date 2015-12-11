package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.MapPointDAOImpl;
import com.btrco.weekmap.model.MapPoint;

import java.util.List;

import static com.btrco.weekmap.dao.MapPointDAOImpl.sessionService;

public class MapPointService {

    private static MapPointDAOImpl mapPointDAO;

    public MapPointService() {
        mapPointDAO = new MapPointDAOImpl();
    }

    public void create(MapPoint mapPoint){
        sessionService.openCurrentSessionWithTransaction();
        mapPointDAO.create(mapPoint);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public void update(MapPoint mapPoint){
        sessionService.openCurrentSessionWithTransaction();
        mapPointDAO.update(mapPoint);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public MapPoint findByID(int id){
        sessionService.openCurrentSession();
        MapPoint mapPoint = mapPointDAO.findById(id);
        sessionService.closeCurrentSession();
        return mapPoint;
    }

    public void delete(MapPoint mapPoint){
        sessionService.openCurrentSessionWithTransaction();
        mapPointDAO.delete(mapPoint);
        sessionService.closeCurrentSessionWithTransaction();
    }

    public List<MapPoint> findAll(){
        sessionService.openCurrentSession();
        List<MapPoint> list = mapPointDAO.findAll();
        sessionService.closeCurrentSession();
        return list;
    }

    public static MapPointDAOImpl getMapPointDAO() {
        return mapPointDAO;
    }
}
