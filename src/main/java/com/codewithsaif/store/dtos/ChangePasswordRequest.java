package com.codewithsaif.store.dtos;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    public String oldPassword;
    public String newPassword;
}
