package listmatchs.security.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/* Classe contenant l'ensemble de la configuration 
 * de la base de données pour Spring boot.
 * ici je modifie la configuration uniquement du Datasource
 * les autres éléments de configuration comme
 * le provider JPA, l'EntityManagerFactory et le TransactionManager
 * sont définies avec les valeurs par défaut par Spring boot
 * grace aux annotations ci-dessous */

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "listmatchs.match.repositories", "listmatchs.security.repositories" })
@ComponentScan(basePackages = { "listmatchs.match", "listmatchs.security" })
@EntityScan(basePackages = { "listmatchs.match.entities", "listmatchs.security.entities" })
@EnableTransactionManagement
public class DatabaseConfig {
  
  //Datasource configuration
  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:8889/db_listmatchs");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    return dataSource;
  }
}
