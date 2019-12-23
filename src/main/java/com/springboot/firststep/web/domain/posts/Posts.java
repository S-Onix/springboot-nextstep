package com.springboot.firststep.web.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    //테이블의 PK와 매핑되는 변수
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //테이블의 각 컬럼에 매핑되는 변수(상태값을 설정할 수 있다)
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //생성자에 빌더패턴을 적용하여 추후 객체 사용시 어떤 필드에 어떤 값이 들어갈지 명확히 알 수 있다.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
