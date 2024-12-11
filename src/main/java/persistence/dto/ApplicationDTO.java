package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

public class ApplicationDTO {

    private int applicationId;
    private LocalDateTime applicationDate;
    private int meal;
    private int preference;

    private int studentId;
    private int dormitoryId;
}
