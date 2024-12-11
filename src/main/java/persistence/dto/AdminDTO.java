package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AdminDTO {
    private int adminCode;
    private String name;
    private boolean gender;
    private String department;
    private String position;
    private String address;
    private String userId;
}
