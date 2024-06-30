package com.example.jpa.user.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

/*
    1. ORM 매핑
 */
// DB 오브젝트와 DB에 있는 컬럼값을 매핑하는 것이 ORM
@Entity(name = "user")
public class UserEntity {
    @Id
    // ID가 어떤 식으로 생성될 것인가 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // db에 따라 GenerationType 달라짐. 오토 인크리먼트로 되도록 지정
    private long id;
    // 나머지 필드는 자동 매핑
    private String name;
    private Integer age;
    private String email;
}
