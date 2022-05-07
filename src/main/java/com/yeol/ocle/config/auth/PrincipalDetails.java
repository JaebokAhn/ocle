package com.yeol.ocle.config.auth;

//
//

import com.yeol.ocle.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티가 로그인처리 요청이 오면 낚아채서 로그인을 진행 
 * 로그인을 진행이 완료가 되면 시큐리티 session을 만들어줌.
 * 
 * Session 오브젝트 => Authentication 타입 객체
 * Authentication 안에 User 정보가 있어야 됨.
 * User 오브젝트 타입 => UserDetails 타입 객체
 *
 * Security Session => Authentication => UserDetails
 */
public class PrincipalDetails implements UserDetails {

    private User user;  //콤포지션

    /**
     * 생성자 (User 정보를 세션에 저장하기 위함)
     * @param user
     */
    public PrincipalDetails(User user) {
        this.user = user;
    }

    /**
     * 해당 User의 권한을 리턴
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    /**
     * 비밀번호 return
     * @return String password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 사용자아이디 return
     * @return  String username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 계정만료되지않음?
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정잠기지않음?
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호만료되지않음?
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정활성화?
     * 
     * 마지막로그인 시간 이후 일정시간 경과시 비활성화
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
