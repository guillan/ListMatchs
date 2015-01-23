package listmatchs.security.boot;

import listmatchs.security.config.SecurityConfig;
import listmatchs.security.entities.Role;
import listmatchs.security.entities.User;
import listmatchs.security.repositories.RoleRepository;
import listmatchs.security.repositories.UserRepository;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.codec.Base64;

public class ListmatchsSecurityApplication {

  private static final Logger logger = Logger.getLogger(ListmatchsSecurityApplication.class);

  public static void main(String[] args) {
    
    SpringApplication app = new SpringApplication(SecurityConfig.class);
    
    ConfigurableApplicationContext context = app.run(args);
    UserRepository userRepository = context.getBean(UserRepository.class);
    RoleRepository roleRepository = context.getBean(RoleRepository.class);

    try {
      String login = "root";
      String password = "root";
      String roleName = String.format("ROLE_GUEST");
        
      //Si le role ROLE_GUEST n'existe pas on le cree
      Role role = roleRepository.findRoleByName(roleName);
      if (role == null) {
        role = roleRepository.save(new Role(roleName));
        logger.info("Role inserted: " + role.toString());
      }
        
      //Si l'utilisateur root/root n'existe pas on le cree
      User user = userRepository.findUserByLogin(login);
      if (user == null) {
        /* le mot de passe est haché avec bcript
         * la partie interessante du code est ici 
         * car je n'ai pas trouvé la fonction équivalente en mySql
         * pour le faire directement avec un insert */
        String pwdCrypt = BCrypt.hashpw(password, BCrypt.gensalt());
        user = userRepository.save(new User(login, pwdCrypt, role));
        logger.info("User inserted: " + user.toString());
      }
        
      //Ici on encode le login:password en Base64
      //Cela permetra plus tard de s'authentifier avec l'entete Authentification:Bazic
      String authBase64 = login + ":" + password;
      byte[] data = Base64.encode(authBase64.getBytes());
      logger.info(new String(data));
    
    } catch (Exception e) {
      logger.error("Exception User", e);
    }
  }
}
