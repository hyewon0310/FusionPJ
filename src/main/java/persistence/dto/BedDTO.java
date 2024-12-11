package main.java.persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BedDTO {
    private String bedNumber;
    private String roomNumber;
    private int dormitoryId;
    private boolean available;
}
