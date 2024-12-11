package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class UnapplicationDTO {

    private int unapplication;
    private LocalDate unapplicationDate;
    private String bankOfAccount; // 은행
    private String accountNum;


    private int studentId;
    private String name;
}
