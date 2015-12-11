package com.btrco.weekmap.model;

import javax.persistence.*;

@Entity
@Table(name = "mappoint")
public class MapPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "longitude")
    private double lng; // долгота
    
    @Column(name = "latitude")
    private double lat; // широта

    public MapPoint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "MapPoint{" +
                "id=" + id +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
