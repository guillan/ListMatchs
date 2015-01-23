package listmatchs.security.services;

import listmatchs.security.entities.User;
import listmatchs.security.repositories.RoleRepository;
import listmatchs.security.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ListmatchsUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userRepository.findUserByLogin(login);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("login [%s] inexistant", login));
    }
    return new ListmatchsUserDetails(user, roleRepository);
  }

}
