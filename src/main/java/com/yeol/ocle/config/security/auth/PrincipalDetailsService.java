package com.yeol.ocle.config.security.auth;

import com.yeol.ocle.model.userinfo.User;
import com.yeol.ocle.repository.usermgmt.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * WebSecurityConfig loginProcessingUrl에 설정되어 있는 URL 요청이 오면
 * 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행 됨
 */
@Service    //메모리 띄움
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * [Security Session].[Authentication].[UserDetails]
     *
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return new PrincipalDetails(user);
        }
        return null;
    }
}
