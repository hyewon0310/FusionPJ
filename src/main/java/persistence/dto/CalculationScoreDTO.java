package main.java.persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CalculationScoreDTO {

    private int scoreId;
    private double totalScore;
    private int applicationId;
    private String regionName;

}
