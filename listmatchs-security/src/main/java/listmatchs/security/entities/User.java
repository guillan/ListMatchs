package listmatchs.security.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/* Choix fait: 1 User a 1 seul role */

@Entity
@Table(name = "T_USER")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "USER_ID")
  private Long id;
    
  @Column(name = "USER_LOGIN")
  private String login;
    
  @Column(name = "USER_PASSWORD")
  private String password;
    
  // un User est lié à un role
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_FK_ROLE_ID")
  private Role role;
  
  // clé étrangère
  @Column(name = "USER_FK_ROLE_ID", insertable = false, updatable = false)
  private long idRole;

  public User() {
  }

  public User(String login, String password, Role role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }
  
  @Override
  public String toString() {
    return String.format("User[%s,%s]", login, password);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
  
  /* Impossible d'inserer ou de mettre a jour la clé étrangère directement
   * Voir ligne 38 donc uniquement un getteur est requis */
  public long getIdRole() {
    return idRole;
  }
}
