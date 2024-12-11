package main.java.persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class DormitoryDTO {

    private int dormitoryId;
    private String dormitoryName;
    private int dormitoryType;
    private int dormitoryCost;
    private boolean gender;
    private int capacity;

    private String bedNumber;
    private String roomNumber;
    private boolean available; // 침대 사용유무

    private boolean status; // 방 상태 (공실/ 이용)

    private int mealType;
    private int mealCost;
}
