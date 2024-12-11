package main.java.persistence.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class UserDTO {
    private String userId;
    private String userPw;
    private boolean userTypeId;
    private String userType;
}
