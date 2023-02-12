package br.com.nava.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = "name")
@Getter @Setter
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
