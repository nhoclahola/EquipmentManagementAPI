package com.nhoclahola.equipmentmanagementapi.exceptions.auth;

public class InvalidUserNameOrPasswordException extends RuntimeException
{
    public InvalidUserNameOrPasswordException()
    {
        super("Tên đăng nhập hoặc mật khẩu không đúng");
    }
}
