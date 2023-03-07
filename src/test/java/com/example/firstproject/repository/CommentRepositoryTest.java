package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest //JPA와 연동한 테스트 시 SpringBootTest 대신 사용
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            //입력 데이터 준비
            Long articleId = 4L;

            //수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            //예상
            Article article = new Article(4L, "당신의 인생 영화는?", "ㄱ");
            Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);

            //검증
            assertEquals(expected.toString(), comments.toString(), "what your favorite move?");

        }

        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            //입력 데이터 준비
            Long articleId = 1L;

            //수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            //예상
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();

            //검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");

        }
    }

    @Test
    void findByNickname() {

        /* Case 1: 'Park'의 모든 댓글 조회 */
        {
            String nickname = "Park";
            List<Comment> comments = commentRepository.findByNickname(nickname);

            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "ㄱ"), nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "ㄱㄱㄱ"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);

            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력!");
        }
    }
}