package xyz.tamanmain.authentication.model.request;

import lombok.Data;

@Data
public class SignInRequest {

    private String username;
    private String password;

}
