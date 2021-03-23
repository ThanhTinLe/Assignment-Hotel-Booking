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
public class RoomErrorDTO implements Serializable {

    String roomIDError;
    String roomNameError;
    String priceError;
    String descriptionError;
    String maxPeopleError;
    String photoLinkError;
    String typeRoomIDError;

    public RoomErrorDTO() {
    }

    public RoomErrorDTO(String roomIDError, String roomNameError, String priceError, String descriptionError, String maxPeopleError, String photoLinkError, String typeRoomIDError) {
        this.roomIDError = roomIDError;
        this.roomNameError = roomNameError;
        this.priceError = priceError;
        this.descriptionError = descriptionError;
        this.maxPeopleError = maxPeopleError;
        this.photoLinkError = photoLinkError;
        this.typeRoomIDError = typeRoomIDError;
    }

    public String getRoomIDError() {
        return roomIDError;
    }

    public void setRoomIDError(String roomIDError) {
        this.roomIDError = roomIDError;
    }

    public String getRoomNameError() {
        return roomNameError;
    }

    public void setRoomNameError(String roomNameError) {
        this.roomNameError = roomNameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getMaxPeopleError() {
        return maxPeopleError;
    }

    public void setMaxPeopleError(String maxPeopleError) {
        this.maxPeopleError = maxPeopleError;
    }

    public String getPhotoLinkError() {
        return photoLinkError;
    }

    public void setPhotoLinkError(String photoLinkError) {
        this.photoLinkError = photoLinkError;
    }

    public String getTypeRoomIDError() {
        return typeRoomIDError;
    }

    public void setTypeRoomIDError(String typeRoomIDError) {
        this.typeRoomIDError = typeRoomIDError;
    }

}
