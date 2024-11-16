package com.nhoclahola.equipmentmanagementapi.configurations;

import com.nhoclahola.equipmentmanagementapi.entities.Role;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import com.nhoclahola.equipmentmanagementapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig
{
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository)
    {
        return args ->
        {
            if (userRepository.findByUsername("admin").isEmpty())
            {
                User user = User.builder()
                        .username("admin")
                        .password("123456")
                        .fullName("Administrator")
                        .gender(true)
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(user);
            }
        };
    }

    @Bean
    public Tika createTika()
    {
        return new Tika();
    }
}
