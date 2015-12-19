package com.btrco.weekmap.service;

import com.btrco.weekmap.dao.MapPointDAO;
import com.btrco.weekmap.dao.MapPointDAOImpl;
import com.btrco.weekmap.model.MapPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MapPointDAOTest {

    MapPointDAO mapPointDAO;

    @Before
    public void before(){
        mapPointDAO = new MapPointDAOImpl();
    }

    @Test
    public void isMapPointFinds(){
        int expectedId = 2;
        MapPoint findedMApPoint = mapPointDAO.findById(expectedId);
        int actualId = findedMApPoint.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void isMapPointCreates(){
        MapPoint mapPoint = new MapPoint();
        mapPoint.setLat(111.222);
        mapPoint.setLng(333.444);

        mapPointDAO.create(mapPoint);
        MapPoint createdMapPoint = mapPointDAO.findById(mapPoint.getId());
        assertEquals(mapPoint, createdMapPoint);
    }

    @Test
    public void isMapPointUpdates(){
        MapPoint oldMapPoint = mapPointDAO.findById(2);
        MapPoint newMapPoint = new MapPoint();

        newMapPoint.setId(oldMapPoint.getId());
        newMapPoint.setLat(oldMapPoint.getLat());
        newMapPoint.setLng(oldMapPoint.getLng());

        newMapPoint.setLat(newMapPoint.getLat() + 10);
        mapPointDAO.update(newMapPoint);
        MapPoint actual = mapPointDAO.findById(newMapPoint.getId());
        assertEquals(newMapPoint, actual);
    }

    @Test
    public void isMapPointsFind(){
        List<MapPoint> list = mapPointDAO.findAll();
        assertTrue(list.size() > 1);
    }

    @Test
    public void isMapPointDeletes(){
        int id = mapPointDAO.findAll().size();
        MapPoint mapPoint = mapPointDAO.findById(id);
        mapPointDAO.delete(mapPoint);
        MapPoint actual = mapPointDAO.findById(id);
        assertNull(actual);
    }

}
