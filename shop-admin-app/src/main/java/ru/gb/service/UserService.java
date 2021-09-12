package ru.gb.service;

import org.springframework.data.domain.Page;
import ru.gb.controller.dto.UserDto;
import ru.gb.controller.dto.UserListParams;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
