package info.cheremisin.social.network.config;

import info.cheremisin.social.network.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	private final UserDetailsServiceImpl userDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/*").permitAll()
			.antMatchers("/user/*").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/*").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/authenticateUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");

//		http.authorizeRequests().antMatchers("/").permitAll().and()
//				.authorizeRequests().antMatchers("/h2-console/**").permitAll();
//		http.csrf().disable();
//		http.headers().frameOptions().disable();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService); //set the custom user details service
		auth.setPasswordEncoder(bCryptPasswordEncoder); //set the password encoder - bcrypt
		return auth;
	}

}






