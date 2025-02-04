//J'ai choisi le design pattern Facade parce qu'il permet de regrouper la logique liée aux utilisateurs en un seul endroit. Au lieu que le UserController appelle directement plusieurs services, il passe par UserFacade, qui s'occupe de tout. Cela rend le code plus clair, plus facile à comprendre et à modifier, car le contrôleur ne gère plus les détails techniques, mais utilise une interface simplifiée.

package org.example.userservice.facade;

import org.example.common.KafkaTopics;
import org.example.common.KafkaEvent;
import org.example.userservice.model.User;
import org.example.userservice.service.UserService;
import org.example.userservice.service.KafkaProducerService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class UserFacade {

    private final UserService userService;
    private final KafkaProducerService kafkaProducerService;

    public UserFacade(UserService userService, KafkaProducerService kafkaProducerService) {
        this.userService = userService;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public Optional<User> getUserById(Long id) {
        return userService.getUserById(id);
    }

    public User createUser(User user) {
        return userService.saveUser(user);
    }

    public User updateUser(Long id, User user) {
        return userService.updateUser(id, user);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
        kafkaProducerService.sendEvent(new KafkaEvent(KafkaTopics.USER_EVENTS, id.toString()));
    }
}
