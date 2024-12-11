package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class StudentDTO {

    private int studentId;
    private String name;
    private String major;
    private boolean gender;
    private String address;
    private int studentTypeId;
    private String userId;

    private int year;
    private int semester;
    private double grade;

    private String dormitoryName;
    private int dormitoryCost;

}
