package br.edu.ulbra.gestaoloja.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ulbra.gestaoloja.model.Role;
import br.edu.ulbra.gestaoloja.model.User;
import br.edu.ulbra.gestaoloja.repository.RoleRepository;
import br.edu.ulbra.gestaoloja.repository.UserRepository;
import br.edu.ulbra.gestaoloja.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public void createUser(User user) {
    	Iterable<Role> rolesIter = this.roleRepository.findAll();
    	Set<Role> roles = new HashSet<Role>(0);
    	
    	for (Role role : rolesIter) {
    		if (role.getName().equals("ADMIN")) {
    			roles.add(role);
    		}
    	}
    	
    	user.setName("user2");
    	user.setUsername("user2");
        user.setPassword(bCryptPasswordEncoder.encode("user2"));
        user.setRoles(roles);
        
        this.userRepository.save(user);
    }
}
