package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserEditRequest;
import com.nhoclahola.equipmentmanagementapi.entities.Role;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import com.nhoclahola.equipmentmanagementapi.exceptions.user.UserNotFoundException;
import com.nhoclahola.equipmentmanagementapi.exceptions.user.UsernameAlreadyExistsException;
import com.nhoclahola.equipmentmanagementapi.mapper.UserMapper;
import com.nhoclahola.equipmentmanagementapi.repositories.UserRepository;
import com.nhoclahola.equipmentmanagementapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> findUsers(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return userRepository.findAll(pageable).stream().toList();
    }

    @Override
    public User createUser(UserCreateRequest request)
    {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new UsernameAlreadyExistsException();
        User user = userMapper.userCreateRequestToUser(request);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId)
    {
        if (!userRepository.existsById(userId))
            throw new UserNotFoundException();
        userRepository.deleteById(userId);
    }

    @Override
    public User editUser(Long userId, UserEditRequest request)
    {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new UsernameAlreadyExistsException();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        userMapper.editUser(user, request);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Long userId)
    {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
    }
}
