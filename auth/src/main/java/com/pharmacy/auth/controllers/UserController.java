package com.pharmacy.auth.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.pharmacy.common.authentication.utils.Constants.ISSUER_INFO;
import static com.pharmacy.common.authentication.utils.Constants.SUPER_SECRET_KEY;
import static com.pharmacy.common.authentication.utils.Constants.TOKEN_EXPIRATION_TIME;

import com.pharmacy.auth.entities.UserEntity;
import com.pharmacy.auth.models.User;
import com.pharmacy.auth.repositories.UserJpaRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

    @Autowired
    @Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/*public UserController(UserRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/users/")
	public void saveUsuario(@RequestBody UserEntity user) {
    	BCrypt.hashpw("25ab7829se78", BCrypt.gensalt());

		user.setPassword(BCrypt.hashpw("25ab7829se78", BCrypt.gensalt()));
		usuarioRepository.save(user);
	}

	@GetMapping("/users/")
	public List<UserEntity> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/users/{username}")
	public UserEntity getUsuario(@PathVariable String username) {
		return usuarioRepository.findByUsername(username);
	}*/

    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user) {
    	User userLogin = new User();
		UserEntity userEntity = userJpaRepository.findByUsername(user.getUsername());
		if ((BCrypt.checkpw(user.getPassword(), userEntity.getPassword()))) { 
		    String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
		            .setSubject(user.getUsername())
		            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
		            .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
		    userLogin.setUsername(user.getPassword());
		    userLogin.setToken(token);
	        return new ResponseEntity<User>(userLogin, HttpStatus.OK);
		}
        return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}
/*
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> savePharmacy(@RequestBody User pharmacy) {
        pharmacy.setCode(UUID.randomUUID().toString());
        User savedPharmacy = pharmacyService.savePharmacy(pharmacy);
        return new ResponseEntity<User>(savedPharmacy, HttpStatus.ACCEPTED);
    }
*/
}
