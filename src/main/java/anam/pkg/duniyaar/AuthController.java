package anam.pkg.duniyaar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anam.pkg.duniyaar.Request.UserRequest;
import anam.pkg.duniyaar.Response.AuthResponse;
import anam.pkg.duniyaar.model.Administrateurs;
import anam.pkg.duniyaar.repo.AdministrateursRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	@Qualifier(value = "user")
	DaoAuthenticationProvider authenticationManagerUser;
	
	@Autowired
	@Qualifier(value = "admin")
	DaoAuthenticationProvider authenticationManagerAdmin;
	
	@Autowired
	AdministrateursRepository adminRepository;
	
	@Autowired
	@Qualifier(value = "admin")
	PasswordEncoder encoder;
	
	@PostMapping("/addAdmin")
	public ResponseEntity<?> addVendeurDeux(@RequestBody Administrateurs admin){

		if (adminRepository.existsByEmail(admin.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email existe déjà!"));
		}
		
		Administrateurs user = new Administrateurs(admin.getNom(), admin.getPrenom(), admin.getTel(),admin.getEmail(), encoder.encode(admin.getPassword()));                       

		adminRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
	}
	
	@PostMapping("/loginAdmin")
	public ResponseEntity<?> login(@RequestBody UserRequest loginRequest) {
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());

		Authentication auth = authenticationManagerAdmin.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		//SString jwtToken = jwtTokenProvider.generateJwtToken(auth);
		Administrateurs admin = adminRepository.findByEmail(loginRequest.getEmail());
		//AuthResponse authResponse = new AuthResponse();
		//authResponse.setMessage("User successfully registered.");
		//authResponse.setAccessToken("Bearer " + jwtToken);
		//authResponse.setAccessToken("Bearer kkjdlljjznnhhbb");
		//authResponse.setRefreshToken(refreshTokenService.createRefreshToken(admin));
		//authResponse.setRefreshToken("ikkkkkkk");
		//authResponse.setUserId(admin.getId());
		
		AuthResponse authResponse = new AuthResponse("User successfully registered.",admin.getId(), "111111", "2222222");
		
		System.out.println(authResponse +"--------------------------------------------------------------------");
		System.out.println(authResponse.getUserId());
		System.out.println("--------------------------------------------------------------------");
		
		
		return ResponseEntity.ok(authResponse);		
	}

}





























