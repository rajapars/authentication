package xyz.tamanmain.authentication.util;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 6503188481482180003L;

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken() {
        return this.jwtToken;
    }

}
