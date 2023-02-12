package br.com.nava.backend.dto;

import br.com.nava.backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class UserDTO implements Serializable {

    private Integer id;

    @Setter
    private String name;

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
    }
}
