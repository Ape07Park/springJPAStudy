package com.example.jpa.user.db;

import org.springframework.data.jpa.repository.JpaRepository;

/*
    2. DB 연결
 */

// JpaRepository<어떤 entity 쓸 것인지, PK 타입>
// 같은 인터페이스라 extends 사용

public interface UserRepository extends JpaRepository<UserEntity, Long> {
// *JPA에서 구현해둔 JpaRepository 상속받는 것 만으로도 쿼리 없어도 db의 데이터 CRUD 가능
}
