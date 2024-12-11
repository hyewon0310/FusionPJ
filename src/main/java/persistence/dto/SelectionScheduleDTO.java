package main.java.persistence.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString

public class SelectionScheduleDTO {
    private String selectionScheduleId;
    private String name;
    private LocalDate period;
//    private List<String> names;
//    private List<String> periods;

    private int dormitoryId;
    private String dormitoryName;
    private int dormitoryType;
    private boolean gender;
    private int dormitoryCost;
    private int capacity;
    private int mealType;
    private int mealCost;

    public SelectionScheduleDTO() {
        this.name = name;
        this.period = period;
    }
}

