package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class RefundDTO {

    private int refundId;
    private LocalDate refundDate;
    private int amomunt;
    private boolean status;
    private int studentId;

    private int name;

    private int selectionId;
}
