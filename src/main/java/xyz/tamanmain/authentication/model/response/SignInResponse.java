package xyz.tamanmain.authentication.model.response;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SignInResponse implements Serializable {

    private static final long serialVersionUID = 6503188481482180003L;

    private String token;

}
