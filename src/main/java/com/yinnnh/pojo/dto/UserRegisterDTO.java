package com.yinnnh.pojo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 50, message = "用户名长度需在1-50个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 1, max = 100, message = "密码长度需在1-100个字符之间")
    private String password;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String nickname;
    private String avatar;//头像地址
}
