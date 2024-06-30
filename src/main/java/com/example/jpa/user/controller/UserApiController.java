package com.example.jpa.user.controller;

import com.example.jpa.user.db.UserEntity;
import com.example.jpa.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    3. 컨트롤러 작성
    해당 api 호출 시 hibernate가 쿼리 생성하고 쿼리는 spring JPA를 통해 자동으로 실행

    * api 호출 - SpringJPA(hibernate 호출 및 JPA의 인터페이스를 구현한 하이버네이트의 특정 기능 실행 )
    - hibernate(쿼리 생성) - jdbc(쿼리 실행) - DB
 */
@RequiredArgsConstructor // 생성자로 UserRepository 주입받기 위함
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    // 전체 불러오기(R)
    @GetMapping("/find-all")
    public List<UserEntity> findAll() {
        // 데이터베이스의 모든 사용자 엔티티를 조회
        return userRepository.findAll();
    }

    // 이름, 나이, 이메일 받아서 저장(C)
    @GetMapping("/save")
    public void autoSave(@RequestParam String name, @RequestParam Integer age, @RequestParam String email) {
        // 새로운 UserEntity 생성
        var user = UserEntity.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();

        System.out.println(user);
        // 데이터베이스에 사용자 저장
        userRepository.save(user);
    }

    // 사용자 정보 업데이트(U)
    @PostMapping("/update")
    public UserEntity updateUser(@RequestParam Long id, @RequestParam String name, @RequestParam Integer age, @RequestParam String email) {
        // 주어진 ID로 기존 사용자 조회
        var user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid user ID: " + id));

        // 사용자 정보 업데이트
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        // 업데이트된 사용자 저장
        return userRepository.save(user);
    }

    // 사용자 제거(D)
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        // 주어진 ID로 사용자 존재 여부 확인
        if (userRepository.existsById(id)) {
            // 사용자 삭제
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid user ID: " + id);
        }
    }
}
