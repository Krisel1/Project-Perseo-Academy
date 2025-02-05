package com.project.Perseo_Academy.dto.response;

import com.project.Perseo_Academy.models.ERole;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class AuthResponse {
    String token;
    ERole role;

    public AuthResponse(String token, ERole role) {
        this.token = token;
        this.role = role;
    }

    private AuthResponse(Builder builder) {
        this.token = builder.token;
        this.role = builder.role;
    }


    public static class Builder {
        private String token;
        private ERole role;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder role(ERole role) {
            this.role = role;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(this);
        }
    }
    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                ", role=" + role +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthResponse that = (AuthResponse) o;
        return Objects.equals(token, that.token) && role == that.role;
    }
    public int hashCode() {
        return Objects.hash(token, role);
    }
}
