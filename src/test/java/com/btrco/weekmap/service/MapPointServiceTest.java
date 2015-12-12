package com.btrco.weekmap.service;

import com.btrco.weekmap.model.MapPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MapPointServiceTest {

    private MapPointService mapPointService;

    @Before
    public void before(){
        mapPointService = new MapPointService();
    }

    @Test
    public void isMapPointServiseNotNull(){
        assertNotNull(mapPointService);
    }

    @Test
    public void isMapPointDAONotNull(){
        assertNotNull(MapPointService.getMapPointDAO());
    }

    @Test
    public void isMapPointFinds(){
        int expectedId = 2;
        MapPoint findedMApPoint = mapPointService.findByID(expectedId);
        int actualId = findedMApPoint.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void isMapPointCreates(){
        MapPoint mapPoint = new MapPoint();
        mapPoint.setLat(111.222);
        mapPoint.setLng(333.444);

        mapPointService.create(mapPoint);
        MapPoint createdMapPoint = mapPointService.findByID(mapPoint.getId());
        assertEquals(mapPoint, createdMapPoint);
    }

    @Test
    public void isMapPointUpdates(){
        MapPoint oldMapPoint = mapPointService.findByID(2);
        MapPoint newMapPoint = new MapPoint();

        newMapPoint.setId(oldMapPoint.getId());
        newMapPoint.setLat(oldMapPoint.getLat());
        newMapPoint.setLng(oldMapPoint.getLng());

        newMapPoint.setLat(newMapPoint.getLat() + 10);
        mapPointService.update(newMapPoint);
        MapPoint actual = mapPointService.findByID(newMapPoint.getId());
        assertEquals(newMapPoint, actual);
    }

    @Test
    public void isMapPointsFind(){
        MapPoint mapPoint = new MapPoint();
        mapPoint.setLat(111.222);
        mapPoint.setLng(333.444);
        MapPoint mapPoint1 = new MapPoint();
        mapPoint1.setLat(111.222);
        mapPoint1.setLng(333.444);
        mapPointService.create(mapPoint);
        mapPointService.create(mapPoint1);

        List<MapPoint> list = mapPointService.findAll();
        assertTrue(list.size() > 1);
    }

    @Test
    public void isMapPointDeletes(){

        int id = mapPointService.findAll().size();
        MapPoint mapPoint = mapPointService.findByID(id);
        mapPointService.delete(mapPoint);
        MapPoint actual = mapPointService.findByID(id);
        assertNull(actual);
    }
}
