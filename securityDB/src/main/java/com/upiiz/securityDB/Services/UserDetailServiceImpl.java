package com.upiiz.securityDB.Services;

import com.upiiz.securityDB.entities.UserEntity;
import com.upiiz.securityDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Requerimos consultar un usuario por su username

        UserEntity userEntity=  UserRepository
                .findUserEntityByUsername (username).
                orElseThrow(()-> new UsernameNotFoundException(username+" not found"));



        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //roles
        userEntity.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority("Role_ "+role.getRolEnum().name()));
        });

        //Permisos
        userEntity.getRoles().stream()
                .flatMap(role-> role.getPermissions().stream())
                .forEach(permission->authorities.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(), authorities);
    }
}
