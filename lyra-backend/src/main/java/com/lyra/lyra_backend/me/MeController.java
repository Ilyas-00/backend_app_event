package com.lyra.lyra_backend.me;

import com.lyra.lyra_backend.role.RoleResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class MeController {

    private final RoleResolver roleResolver;

    public MeController(RoleResolver roleResolver) {
        this.roleResolver = roleResolver;
    }

    @GetMapping
    public ResponseEntity<MeResponse> getMe(@AuthenticationPrincipal Jwt jwt) {
        String tgi = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(new MeResponse(
                tgi,
                roleResolver.getRole(tgi),
                roleResolver.getServiceId(tgi)
        ));
    }
}
