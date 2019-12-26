package com.springboot.firststep.service.posts;

import com.springboot.firststep.web.domain.posts.Posts;
import com.springboot.firststep.web.domain.posts.PostsRepository;
import com.springboot.firststep.web.dto.PostsListResponseDto;
import com.springboot.firststep.web.dto.PostsResponseDto;
import com.springboot.firststep.web.dto.PostsSaveRequestDto;
import com.springboot.firststep.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    //stream과 람다식 공부하기
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
//        return postsRepository.findAll().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        return postsRepository.findAll().stream().map(posts -> new PostsListResponseDto(posts)).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
}
