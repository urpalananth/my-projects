package in.inst.photoalbummanager.security;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.inst.photoalbummanager.beans.User;
import in.inst.photoalbummanager.repo.UserRepository;

@Transactional
public class PAMUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger("PAMUserDetailsService");

    private UserRepository userRepository;

    public PAMUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByName(username);
            if (user == null) {
                System.out.println("user not found with the provided username");
                return null;
            }
            LOGGER.info("User from username " + user.toString());
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthorities(user));
        }
        catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        String[] roles = user.getRoles().split(",");
        
        for(String role : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(grantedAuthority);
        }
        LOGGER.info("user authorities are " + authorities.toString());
        return authorities;
    }
}