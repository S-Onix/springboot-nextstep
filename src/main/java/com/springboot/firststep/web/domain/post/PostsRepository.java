package com.springboot.firststep.web.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

//Entity 클래스와 기본 Respository는 함께 위치해야한다.
//보통 패키지에 한세트로 묶어 관리한다.
//JpaRepository<Entity class, PK type> 을 상속받는다.
//기본적인 CRUD 메소드가 자동으로 생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
