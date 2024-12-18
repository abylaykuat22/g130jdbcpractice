package kz.bitlab.g130springjdbc2.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private Long id;
    private String name;
    private Double price;
    private Brand brand;
}
