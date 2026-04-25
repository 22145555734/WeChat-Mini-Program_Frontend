package com.yinnnh.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String token; // JWT Token
}
