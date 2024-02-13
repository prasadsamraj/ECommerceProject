package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GenericUserDto {
    private String email;
    private Set<Role> roles = new HashSet<>();
    public static GenericUserDto from(User user){
        GenericUserDto genericUserDto = new GenericUserDto();
        genericUserDto.setEmail(user.getEmail());
        genericUserDto.setRoles(user.getRoles());
        return genericUserDto;
    }
}
