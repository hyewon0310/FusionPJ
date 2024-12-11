package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class RoomDTO {
    private int roomNumber;
    private int dormitoryId;
    private boolean status;

    private String bedNumber;
    private int dormitoryType;
    private int capacity;
}
