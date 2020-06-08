package ca.sheridancollege.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ca.sheridancollege.Security.UserDetailsServiceImpl;
import ca.sheridancollege.Security.LoginAccessDeniedHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	 
	
	@Autowired
	LoginAccessDeniedHandler accessDeniedHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
		.antMatchers("/buyer/**").hasRole("BUYER") 
		.antMatchers("/realtor/**").hasRole("REALTOR") 
		//.antMatchers("/guest/**").hasRole("GUEST")
		.antMatchers(HttpMethod.POST,"/somePostURL").hasRole("SOMEROLE")
		.antMatchers(HttpMethod.POST, "/register").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.GET, "/view").hasAnyRole("BUYER", "REALTOR")
		.antMatchers(HttpMethod.GET, "/user").hasAnyRole("BUYER", "REALTOR")
		.antMatchers(HttpMethod.GET, "/search/property/commercial").hasAnyRole("BUYER", "REALTOR")
		.antMatchers(HttpMethod.GET, "/search/property/residential").hasAnyRole("BUYER", "REALTOR")
		.antMatchers(HttpMethod.GET, "/add/property/commercial").hasRole("REALTOR")
		.antMatchers(HttpMethod.GET, "/add/property/residential").hasRole("REALTOR")
		.antMatchers(HttpMethod.GET, "/email").hasRole("REALTOR")
		.antMatchers("/","/css/**","/images/**","/js/**","/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)//clear session
		.clearAuthentication(true)//clear cookies
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()//access denied
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);	
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	 UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception{
		  auth.userDetailsService(userDetailsService)
		  .passwordEncoder(passwordEncoder());
	  }
	
}
