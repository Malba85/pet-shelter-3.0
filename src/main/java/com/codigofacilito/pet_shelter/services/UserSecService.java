package com.codigofacilito.pet_shelter.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codigofacilito.pet_shelter.models.security.UserSecEntity;
import com.codigofacilito.pet_shelter.models.security.RoleSecEntity;

import com.codigofacilito.pet_shelter.repositories.RoleSecRepository;

import com.codigofacilito.pet_shelter.repositories.UserSecRepository;

import jakarta.annotation.PostConstruct;

@Service
// public class UserSecService {
public class UserSecService implements UserDetailsService{
    @Autowired
    private UserSecRepository userSecRepository;
    @Autowired
    private RoleSecRepository roleSecRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Crear un nuevo usuario con un conjunto de roles
    public UserSecEntity createUser(UserSecEntity user) {
        // user.setPassword((user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userSecRepository.save(user);
    }

    // Obtener un usuario por ID
    public Optional<UserSecEntity> getUserById(Long id) {
        return userSecRepository.findById(id);
    }

    // Obtener todos los usuarios
    public List<UserSecEntity> getAllUsers() {
        return userSecRepository.findAll();
    }

    // Actualizar un usuario y sus roles
    public UserSecEntity updateUser(Long id, UserSecEntity userDetails) {
        UserSecEntity user = userSecRepository.findById(id).orElseThrow();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        // user.setPassword((userDetails.getPassword()));
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        return userSecRepository.save(user);
    }

    // Eliminar un usuario
    public void deleteUser(Long id) {
        userSecRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserSecEntity user = userSecRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(RoleSecEntity::getName).toArray(String[]::new))
                .build();
    }

    @PostConstruct
    public void initDatabase() {
        if (userSecRepository.count() == 0) {
            RoleSecEntity roleManager = new RoleSecEntity("MANAGER");
            RoleSecEntity roleUser = new RoleSecEntity("USER");

            roleSecRepository.saveAll(List.of(roleManager, roleUser));

            if (userSecRepository.count() == 0) {
                UserSecEntity user1 = new UserSecEntity();
                user1.setName("John Doe");
                user1.setEmail("johndoe@mail.com");
                // user1.setPassword("mf1234");
                user1.setPassword(passwordEncoder.encode("jd1234"));
                user1.setRoles(new HashSet<>(Set.of(roleManager)));

                UserSecEntity user2 = new UserSecEntity();
                user2.setName("Jane Lewis");
                user2.setEmail("janelewis@mail.com");
                // user2.setPassword("jp1234");
                user2.setPassword(passwordEncoder.encode("jl1234"));
                user2.setRoles(new HashSet<>(Set.of(roleUser)));

                userSecRepository.saveAll(List.of(user1, user2));
            }
        }
    }
}
