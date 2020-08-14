package com.example.test.auth;

import com.example.test.models.Client;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserFormClientsService(username);
    }

    private UserDetails loadUserFormClientsService(String username) throws UsernameNotFoundException {

        RestTemplate rest = new RestTemplate();
        Client client = rest.getForObject(
                "http://localhost:32923/clientbyemail/"+username,
                Client.class);
        if (client != null) {
            return new ApplicationUser(
                    client.getEmail(),
                    client.getPassword()
            );
        }

        throw new UsernameNotFoundException("not found");
    }
}
