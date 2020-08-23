package com.supermarket.employeeUI.auth;



import com.supermarket.employeeUI.models.Employee;
import com.supermarket.employeeUI.proxies.RequestHandler;
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
        handler.setServiceKey("employee-by-email")
                .setServiceVersion(-1)
                .setAdditionnelParamsToUrl(email)
                .setResponseType(Employee.class);

        try {
            Employee employee = (Employee) handler.handle();

            return new ApplicationUser(
                    employee.getId(),
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getRole()
            );
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("not found");
        }
    }
}
