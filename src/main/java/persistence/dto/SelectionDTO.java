package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class SelectionDTO {
    private int applicationId;
       private int studentId;
       private int studentTypeId;
       private int preference;
       private Double totalScore;
       private int dormitoryId;
       private int capacity;
       private String bedNum; // 배정된 침대 번호
       private String roomNum; // 배정된 방 번호
}
