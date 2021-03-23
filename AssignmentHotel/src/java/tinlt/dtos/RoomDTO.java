/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinlt.dtos;

import java.io.Serializable;

/**
 *
 * @author Ray Khum
 */
public class RoomDTO implements Serializable {

    String roomID;
    String roomName;
    float price;
    String description;
    int maxPeople;
    String photoLink;
    String typeRoomID;

    public RoomDTO(String roomID, String roomName, float price, String description, int maxPeople, String photoLink, String typeRoomID) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.price = price;
        this.description = description;
        this.maxPeople = maxPeople;
        this.photoLink = photoLink;
        this.typeRoomID = typeRoomID;
    }

    public RoomDTO() {
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getTypeRoomID() {
        return typeRoomID;
    }

    public void setTypeRoomID(String typeRoomID) {
        this.typeRoomID = typeRoomID;
    }

    @Override
    public String toString() {
        return "RoomDTO{" + "roomID=" + roomID + ", roomName=" + roomName + ", price=" + price + ", description=" + description + ", maxPeople=" + maxPeople + ", photoLink=" + photoLink + ", typeRoomID=" + typeRoomID + '}';
    }

}
