package org.example.config;

import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.sql.DriverManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "org.example")
public class AppConfig {

   private final Environment env;

   @Autowired
   public AppConfig(Environment env) {
      this.env = env;
   }

   @Bean
   public DataSource getDataSource() throws SQLException {

      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }
   private Properties hibernateProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
      properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
      properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
      return properties;
   }

   @Bean
   public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
      JpaTransactionManager manager = new JpaTransactionManager();
      manager.setEntityManagerFactory(entityManagerFactory);
      return manager;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
      HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
      jpaVendorAdapter.setGenerateDdl(true);
      LocalContainerEntityManagerFactoryBean entityManagerFactory =
              new LocalContainerEntityManagerFactoryBean();
      entityManagerFactory.setDataSource(dataSource);
      entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
      entityManagerFactory.setPackagesToScan("org.example.models");
      entityManagerFactory.setJpaProperties(hibernateProperties());
      return entityManagerFactory;
   }
   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
