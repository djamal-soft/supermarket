package com.supermarket.clientUI.auth;


import com.supermarket.clientUI.models.Client;
import com.supermarket.clientUI.proxies.RequestHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loadUserFormClientsService(email);
    }

    private UserDetails loadUserFormClientsService(String email) throws UsernameNotFoundException {

        RequestHandler handler = new RequestHandler();
        handler.setServiceKey("client-by-email")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(email)
                .setResponseType(Client.class);

        try {
            Client client = (Client) handler.handle();
            return new ApplicationUser(
                    client.getId(),
                    client.getEmail(),
                    client.getPassword(),
                    client.getAddress());
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("not found");
        }
    }
}
