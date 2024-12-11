package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class MealDTO {
    private int mealType;
    private int mealCost;
    private int dormitoryId;
}
