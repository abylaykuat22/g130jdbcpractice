package kz.bitlab.g130springjdbc2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    private Long id;
    private String name;
    private String code;
}
