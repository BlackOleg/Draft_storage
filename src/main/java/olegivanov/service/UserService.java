package ru.netology.cloudstorage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.cloudstorage.entity.User;
import ru.netology.cloudstorage.repository.RoleRepository;
import ru.netology.cloudstorage.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


//    @Autowired
//    public UserService(UserRepository repository) {
//        this.userRepository = repository;
//    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(
                () ->
                        new UsernameNotFoundException(
                                String.format("User with username - %s, not found", username))
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList())
        );

    }

}
