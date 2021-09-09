package com.yongwoonlim.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    @Size(min = 2, max = 20, message = "Name은 2 - 20 사이로 입력해주세요")
    private String name;
    @Past
    private Date joinDate;
}
