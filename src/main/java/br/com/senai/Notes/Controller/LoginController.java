package br.com.senai.Notes.Controller;

import br.com.senai.Notes.Service.UsuarioService;
import br.com.senai.Notes.dtos.LoginRequest;
import br.com.senai.Notes.dtos.LoginResponseDTO;
import br.com.senai.Notes.dtos.UsuarioListarDTO;
import br.com.senai.Notes.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class LoginController {


    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    private final UsuarioService usuarioService;


    public LoginController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.usuarioService = usuarioService;
    }



    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        var authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);

        Instant now = Instant.now();
        long validade = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("urbanswift-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(validade))
                .subject(auth.getName())
                .claim("roles", auth.getAuthorities())
                  .build();

        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

        Usuario usuario = usuarioService.findByEmail(loginRequest.getEmail());

        UsuarioListarDTO dto = new UsuarioListarDTO();
        dto.setEmail(loginRequest.getEmail());
        dto.setId(usuario.getUsuarioId());

        return ResponseEntity.ok(new LoginResponseDTO(token, dto));

    }


}
