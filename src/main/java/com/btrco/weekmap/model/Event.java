package com.btrco.weekmap.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event implements Serializable{

    public enum EventType{
        default_type
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Type(type = "text")
    @Column(name = "shortDescription")
    private String shortDescription;

    @Type(type = "text")
    @Column(name = "description")
    private String fullDescription;

    @Type(type = "java.sql.Timestamp")
    @Column(name = "datetime")
    private LocalDateTime dateTimeOfEvent;
    //TODO: add photo. Which type select for it?

    @ManyToOne
    @JoinColumn(name = "mapPoint_id")
    private MapPoint mapPoint;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //TODO: price? some other features???

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EventType getType() {
        return type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public Timestamp getDateTimeOfEvent() {
        return Timestamp.valueOf(dateTimeOfEvent);
    }

    public MapPoint getMapPoint() {
        return mapPoint;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setDateTimeOfEvent(Timestamp dateTimeOfEvent) {
        this.dateTimeOfEvent = dateTimeOfEvent.toLocalDateTime();
    }

    public void setMapPoint(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUser(User creator) {
        this.user = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (type != event.type) return false;
        if (shortDescription != null ? !shortDescription.equals(event.shortDescription) : event.shortDescription != null)
            return false;
        if (fullDescription != null ? !fullDescription.equals(event.fullDescription) : event.fullDescription != null)
            return false;
        if (dateTimeOfEvent != null ? !dateTimeOfEvent.equals(event.dateTimeOfEvent) : event.dateTimeOfEvent != null)
            return false;
        if (mapPoint != null ? !mapPoint.equals(event.mapPoint) : event.mapPoint != null) return false;
        if (address != null ? !address.equals(event.address) : event.address != null) return false;
        return !(user != null ? !user.equals(event.user) : event.user != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (fullDescription != null ? fullDescription.hashCode() : 0);
        result = 31 * result + (dateTimeOfEvent != null ? dateTimeOfEvent.hashCode() : 0);
        result = 31 * result + (mapPoint != null ? mapPoint.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
