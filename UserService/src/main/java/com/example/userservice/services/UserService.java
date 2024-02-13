package com.example.userservice.services;

import com.example.userservice.dtos.GenericUserDto;
import com.example.userservice.exceptions.InvalidRoleIdException;
import com.example.userservice.exceptions.InvalidUserCredentialsException;
import com.example.userservice.exceptions.InvalidUserIdException;
import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public GenericUserDto getUserDetails(String userId) throws InvalidUserIdException {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(InvalidUserIdException::new);
        return GenericUserDto.from(user);
    }

    public GenericUserDto setUserRoles(String userId, List<String> roleIds) throws InvalidUserCredentialsException, InvalidRoleIdException {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(InvalidUserCredentialsException::new);
        Set<Role> userRoles = user.getRoles();
        List<Optional<Role>> optionalRoles = roleIds.stream().map(UUID::fromString).map(roleRepository::findById).toList();
        for(Optional<Role> optionalRole:optionalRoles){
            if(optionalRole.isEmpty()) throw new InvalidRoleIdException();
            userRoles.add(optionalRole.get());
        }
        userRepository.save(user);
        return GenericUserDto.from(user);
    }
}
