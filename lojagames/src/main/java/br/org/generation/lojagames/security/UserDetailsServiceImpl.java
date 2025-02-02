package br.org.generation.lojagames.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.org.generation.lojagames.model.Usuario;
import br.org.generation.lojagames.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		usuario.orElseThrow(()-> new UsernameNotFoundException(userName+ " not found."));
		
		return usuario.map(UserDetailsImpl::new).get();
	}
}