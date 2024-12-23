package kz.bitlab.g130jdbcpractice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationRequest {

    private Long id;
    private String userName;
    private String courseName;
    private String commentary;
    private String phone;
    private boolean handled; //обработано или нет
}
