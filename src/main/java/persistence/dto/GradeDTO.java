package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class GradeDTO {
    private int year;
    private int semester;
    private double grade;

    private int studentId;
    private int adminCode;
}
