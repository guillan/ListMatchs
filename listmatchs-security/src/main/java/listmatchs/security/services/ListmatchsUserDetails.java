package listmatchs.security.services;

import java.util.ArrayList;
import java.util.Collection;

import listmatchs.security.entities.Role;
import listmatchs.security.entities.User;
import listmatchs.security.repositories.RoleRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ListmatchsUserDetails implements UserDetails {

  private static final long serialVersionUID = 1L;

  private User user;
  private RoleRepository roleRepository;

  public ListmatchsUserDetails() {
  }

  public ListmatchsUserDetails(User user, RoleRepository roleRepository) {
    this.user = user;
    this.roleRepository = roleRepository;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    /* Etant donnée que la clé étrangère est en lazy dans la classe User,
     * le role n'est pas récupéré dans l'objet User.
     * Pour avoir le role de l'user on est donc obligé de le recuperer par l'id */
    Role role = roleRepository.findOne(user.getId());
    authorities.add(new SimpleGrantedAuthority(role.getName()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
