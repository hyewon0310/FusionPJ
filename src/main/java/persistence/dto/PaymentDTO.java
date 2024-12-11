package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString

public class PaymentDTO {

    private int paymentId;
    private LocalDate paymentDate;
    private int paymentCost;
    private int selectionId;

    private int studentId;
    private String name;
}
