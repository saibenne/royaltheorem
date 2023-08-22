package com.royaltheorem.UserService;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {


    int userId;
    int designId;
    LocalDateTime date;
    String status;


}
