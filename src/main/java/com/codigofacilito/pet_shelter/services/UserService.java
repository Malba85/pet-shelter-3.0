package com.codigofacilito.pet_shelter.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codigofacilito.pet_shelter.models.users.NewUser;
import com.codigofacilito.pet_shelter.models.users.UserEntity;
import com.codigofacilito.pet_shelter.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<UserEntity> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public UserEntity addUser(NewUser newUser) {
    return userRepository
        .saveAndFlush(new UserEntity(newUser.name(), newUser.email(), newUser.phone()));
  }

  public void updateUser(Long id, UserEntity updatedUser) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      UserEntity existingUser = user.get();
      existingUser.setName(updatedUser.getName());
      existingUser.setEmail(updatedUser.getEmail());
      existingUser.setPhone(updatedUser.getPhone());
      userRepository.saveAndFlush(existingUser);
    }
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @PostConstruct
  void initDatabase() {
    userRepository.save(new UserEntity("John Doe", "johndoe@mail.com", "1234567890"));
    userRepository.save(new UserEntity("Jane Doe", "janedoe@mail.com", "0987654321"));
    userRepository.save(new UserEntity("John Smith", "johnsmith@mail.com", "1234567890"));
    userRepository.save(new UserEntity("Alice Johnson", "alice@mail.com", "1112223333"));
    userRepository.save(new UserEntity("Bob Williams", "bob@mail.com", "4445556666"));
    userRepository.save(new UserEntity("Charlie Brown", "charlie@mail.com", "7778889999"));
    userRepository.save(new UserEntity("David Wilson", "david@mail.com", "1011121314"));
    userRepository.save(new UserEntity("Emma Davis", "emma@mail.com", "1516171819"));
    userRepository.save(new UserEntity("Frank Miller", "frank@mail.com", "2021222324"));
    userRepository.save(new UserEntity("Grace Lee", "grace@mail.com", "2526272829"));

  }
}
