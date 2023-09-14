package test.server.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import test.server.demo.user.User;
import test.server.demo.user.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    final
    UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyUserDetailsService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        // Create default user
        User normalUser = new User();
        normalUser.setUserName("username");
        normalUser.setPassword("password");
        saveUser(normalUser);
    }


    // UserDetails is the representation of the login request of a user
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyUserDetails::new).get();
    }

    // Add new user entries to the database
    public User saveUser(User newUser) {
        // Encrypt password in case of a dataleak ;)
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }
}
