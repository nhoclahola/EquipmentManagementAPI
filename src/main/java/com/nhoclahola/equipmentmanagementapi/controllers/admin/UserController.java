package com.nhoclahola.equipmentmanagementapi.controllers.admin;

import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserEditRequest;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController
{
    private final UserService userService;

    @GetMapping("/users/count")
    public ResponseEntity<Long> getUsersCount()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.count());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        List<User> users = userService.findUsers(pageNumber - 1);
        return ResponseEntity.status(HttpStatus.OK)
                .body(users);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(request));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody @Valid UserEditRequest request)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.editUser(id, request));
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> getSearchUser(@RequestParam("query") String query, @RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.searchUser(query, pageNumber - 1));
    }

    @GetMapping("/users/search/count")
    public ResponseEntity<Long> getCountSearchUser(@RequestParam("query") String query)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.countSearchUser(query));
    }
}
