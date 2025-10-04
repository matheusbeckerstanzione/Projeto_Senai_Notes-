package br.com.senai.Notes.Controller;

import br.com.senai.Notes.dtos.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class LoginController {


    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;


    public LoginController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
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

        // 6. Define o cabeçalho do token, especificando o algoritmo de assinatura.
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

        // 7. Usa a "Máquina de Criar Tokens" para gerar a string final, combinando o cabeçalho
        // e o payload, e assinando tudo com nossa chave secreta.
        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

        // 8. Retorna o token gerado para o cliente com um status 200 OK.
        return ResponseEntity.ok(token);
    }
}