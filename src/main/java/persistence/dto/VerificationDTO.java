package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.*;


@Getter
@Setter
@ToString

public class VerificationDTO {
    private int verificationId;
    private String path;
    private LocalDateTime date;

    private int selectionId; // 선발id

    private String studentId;
    private String studentName;
}
