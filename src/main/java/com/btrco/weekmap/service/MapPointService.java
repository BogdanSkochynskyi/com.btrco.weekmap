package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.MapPointDAOImpl;
import com.btrco.weekmap.model.MapPoint;

import java.util.List;

public class MapPointService {

    private static MapPointDAOImpl mapPointDAO;

    public MapPointService() {
        mapPointDAO = new MapPointDAOImpl();
    }

    public void create(MapPoint mapPoint){
        mapPointDAO.openCurrentSessionWithTransaction();
        mapPointDAO.create(mapPoint);
        mapPointDAO.closeCurrentSessionWithTransaction();
    }

    public void update(MapPoint mapPoint){
        mapPointDAO.openCurrentSessionWithTransaction();
        mapPointDAO.update(mapPoint);
        mapPointDAO.closeCurrentSessionWithTransaction();
    }

    public MapPoint findByID(int id){
        mapPointDAO.openCurrentSession();
        MapPoint mapPoint = mapPointDAO.findById(id);
        mapPointDAO.closeCurrentSession();
        return mapPoint;
    }

    public void delete(MapPoint mapPoint){
        mapPointDAO.openCurrentSessionWithTransaction();
        mapPointDAO.delete(mapPoint);
        mapPointDAO.closeCurrentSessionWithTransaction();
    }

    public List<MapPoint> findAll(){
        mapPointDAO.openCurrentSession();
        List<MapPoint> list = mapPointDAO.findAll();
        mapPointDAO.closeCurrentSession();
        return list;
    }

    public static MapPointDAOImpl getMapPointDAO() {
        return mapPointDAO;
    }
}
