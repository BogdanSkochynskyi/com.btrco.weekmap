package com.btrco.weekmap.service;

import com.btrco.weekmap.model.MapPoint;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void isMapPointCreates(){
        MapPoint mapPoint = new MapPoint();
        mapPoint.setLat(111.222);
        mapPoint.setLng(333.444);

        mapPointService.create(mapPoint);
        MapPoint createdMapPoint = mapPointService.findByID(mapPoint.getId());
        assertEquals(mapPoint, createdMapPoint);
    }
}
