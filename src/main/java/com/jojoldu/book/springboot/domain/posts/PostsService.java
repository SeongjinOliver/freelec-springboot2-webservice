package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @org.springframework.transaction.annotation.Transactional
  public Long save(PostsSaveRequestDto requestDto){
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @org.springframework.transaction.annotation.Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts = postsRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    posts.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  public PostsResponseDto findById (Long id) {
    Posts entity = postsRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    return new PostsResponseDto(entity);
  }

  @org.springframework.transaction.annotation.Transactional(readOnly = true)
  public List<PostsListResponseDto> findAllDesc() {
    return postsRepository.findAllDesc().stream()
        .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))와 같음
        .collect(Collectors.toList());
  }

  @Transactional
  public void delete (Long id) {
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    postsRepository.delete(posts);
  }

}
