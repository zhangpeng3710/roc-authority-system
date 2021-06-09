package org.roc.roc.auth.service;

import org.roc.roc.common.entity.RocAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RocUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RocUserDetailService(PasswordEncoder passwordEncoder) {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RocAuthUser user = new RocAuthUser();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode("123456"));

        return new User(username, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }
}
