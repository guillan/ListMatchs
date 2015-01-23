package listmatchs.security.tests;

import java.util.List;

import listmatchs.security.config.SecurityConfig;
import listmatchs.security.entities.Role;
import listmatchs.security.entities.User;
import listmatchs.security.repositories.RoleRepository;
import listmatchs.security.repositories.UserRepository;
import listmatchs.security.services.ListmatchsUserDetails;
import listmatchs.security.services.ListmatchsUserDetailsService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/* Quelques tests junit pour tester que tout fonctionne correctement
 * pour Spring security */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SecurityConfig.class)
public class SecurityTest {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private ListmatchsUserDetailsService listmatchsUserDetailsService;

  @Test
  public void findUserByLogin() {
    // find User root
    User user = userRepository.findUserByLogin("root");
    Role role = roleRepository.findOne(user.getId());
    
    // verification si le mot de passe est root
    Assert.assertTrue(BCrypt.checkpw("root", user.getPassword()));
    
    // verification que le role est ROLE_GUEST
    Assert.assertEquals("ROLE_GUEST", role.getName());
  }
  
  @Test
  public void loadUserByUsername() {
    // on récupère l'utilisateur [admin]
    ListmatchsUserDetails userDetails = (ListmatchsUserDetails) 
        listmatchsUserDetailsService.loadUserByUsername("root");
    
    // verification si le mot de passe est root
    Assert.assertTrue(BCrypt.checkpw("root", userDetails.getPassword()));
    
    // verification que le role est ROLE_GUEST
    @SuppressWarnings("unchecked")
    List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) 
        userDetails.getAuthorities();
    Assert.assertEquals(1L, authorities.size());
    Assert.assertEquals("ROLE_GUEST", authorities.get(0).getAuthority());
  }
}
