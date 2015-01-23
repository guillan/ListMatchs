package listmatchs.security.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ROLE")
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ROLE_ID")
  private Long id;
  
  @Column(name = "ROLE_NAME")
  private String name;

  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Role[%s]", name);
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
