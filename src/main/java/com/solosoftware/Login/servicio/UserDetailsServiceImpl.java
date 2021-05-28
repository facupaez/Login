//package com.solosoftware.Login.servicio;
//
//import com.solosoftware.Login.dao.UsuarioDao;
//import java.util.HashSet;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.solosoftware.Login.entidad.Rol;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UsuarioDao usuarioDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        com.solosoftware.Login.entidad.Usuario appUser = usuarioDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email inválido"));
//        
//        Set grantList = new HashSet();
//        
//        for(Rol rol : appUser.getRoles()){
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.getDescripcion());
//            grantList.add(grantedAuthority);
//        }
//        
//        UserDetails usuario = (UserDetails) new User(email,appUser.getPassword(),grantList);
//        
//        return usuario;
//    }
//
//
//}
