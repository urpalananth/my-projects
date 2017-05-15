package in.inst.photoalbummanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import in.inst.photoalbummanager.repo.UserRepository;

@Configuration
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private UserRepository userRepository;
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/js/**", "/bootstrap/css/**").permitAll()
            .and()
				.authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            .and()
            	.formLogin().permitAll()
            	.and()
    			.httpBasic()
    		
            .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsServiceBean());
    }
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new PAMUserDetailsService(userRepository);
    }
}
