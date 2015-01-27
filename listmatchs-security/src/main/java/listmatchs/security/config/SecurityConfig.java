package listmatchs.security.config;

import listmatchs.security.services.ListmatchsUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* Classe contenant la config de Spring security */

@EnableAutoConfiguration
@EnableWebSecurity
@Import({ DatabaseConfig.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private ListmatchsUserDetailsService listmatchsUserDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder registry) throws Exception {
    // authentification faite par le bean listmatchsUserDetailsService
    // le mot de passe est crypté par l'algorithme de hachage BCrypt
    registry.userDetailsService(listmatchsUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    /* Pour facilité l'implémentation, j'ai désactivé CSRF. 
     * Pour une application de prod il est crucial d'activer
     * CSRF afin de se proteger des attaques de type CSRF*/
    http.csrf().disable();
    
    /* authentification via le header Authorization: Basic xxxx
     * J'utilise le header Authorization: Basic car c'est le plus facile a mettre
     * en place. Cependant il possede quelaue faille de securite et ne doit pas être
     * utilisé pour une vrai application en production */
    http.httpBasic();
    
    // le dossier listmatchs est accessible à tous
    http.authorizeRequests().antMatchers(HttpMethod.GET, "/listmatchs", "/listmatchs/**").permitAll();

    /* Pour acceder a l'ensemble de l'application il faut être 
     * authentifié avec un role GUEST */
    http.authorizeRequests() //
        .antMatchers("/", "/**") // toutes les URL
        .hasRole("GUEST");
  }
}
