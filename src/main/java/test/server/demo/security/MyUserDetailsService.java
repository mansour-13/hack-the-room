package test.server.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // UserDetails is the representation of the login request of a user
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.err.println("Starting the method  loadByUserName:" + userName);
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isPresent()){
            System.out.println("user = " + user);
        }else{
            System.err.println("Did not find a user :(");
        }

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
