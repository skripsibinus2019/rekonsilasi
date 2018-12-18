package com.rekonsiliasi.config;
 
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rekonsiliasi.validator.RoleValidator;
import com.rekonsiliasi.validator.UsersValidator;
 
@Configuration
@ComponentScan("com.rekonsiliasi.*")
@Import({ SecurityConfig.class })
@EnableTransactionManagement
 
 
public class ApplicationContextConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}
 
   // The Environment class serves as the property holder
   // and stores all the properties loaded by the @PropertySource
   @Autowired
   private Environment env;
 
   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
 
       viewResolver.setPrefix("/WEB-INF/pages/");
       viewResolver.setSuffix(".jsp");
 
       return viewResolver;
   }
 
   @Bean(name = "dataSource")
   public DataSource getDataSource() {
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
       // See: datasouce-cfg.properties
       dataSource.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
//       dataSource.setUrl("jdbc:jtds:sqlserver://localhost:1433;instanceName=.;databaseName=rekonsiliasi;sendStringParametersAsUnicode=false;");
//       dataSource.setUrl("jdbc:jtds:sqlserver://localhost:16969;instanceName=.;databaseName=rekonsiliasi;sendStringParametersAsUnicode=false;");
       dataSource.setUrl("jdbc:jtds:sqlserver://rekonsiliasi.database.windows.net:1433;instanceName=rekonsiliasi.database.windows.net;databaseName=rekonsiliasi;user=skripsi@rekonsiliasi;password=r4h4s14!!!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;sendStringParametersAsUnicode=false;");
       
       
       dataSource.setUsername("sa");
       dataSource.setPassword("apaajadeh");
 
       System.out.println("## getDataSource: " + dataSource);
 
       return dataSource;
   }
 
   @Bean(name = "transactionManager")
   public DataSourceTransactionManager getTransactionManager() {
       DataSourceTransactionManager txManager = new DataSourceTransactionManager();
 
       DataSource dataSource = this.getDataSource();
       txManager.setDataSource(dataSource);
 
       return txManager;
   }
   
   @Bean(name = "roleValidator")
   public RoleValidator roleValidator() {
	   return new RoleValidator();
   }
   
   @Bean(name = "usersValidator")
   public UsersValidator usersValidator() {
	   return new UsersValidator();
   }
   
   @Bean(name = "messageSource")
   public ReloadableResourceBundleMessageSource getMessageSource() {
	   ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	   
	   messageSource.setBasename("classpath:messages");
	   messageSource.setDefaultEncoding("UTF-8");
	   
	   return messageSource;
   }
   
   

}