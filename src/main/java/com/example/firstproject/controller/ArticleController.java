package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        //dto 에 전달된 값을 entity 로 변환
        Article article = form.toEntity();

        //repository 를 활용해 entity 를 db에 저장
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        //id로 DB에 저장된 데이터를 가져옴, 아래 둘 중 하나 사용
        //Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);

        List<CommentDto> commentDtos = commentService.comments(id);

        //가져온 데이터를 model에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        //페이지 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //일반적인 List 데이터 주입 시 같은 자료형을 갖는 변수 생성 후 주입
        //Iterable<Article> articleEntityList = articleRepository.findAll();

        //다른 자료형으로 캐스팅(변환) 해서 받고 싶은 경우 현재 사용중인 interface의 메서드를 override 후 재정의 해서 사용
        //ArrayList 의 경우, 상위 타입인 List 로 받을 수 있음
        List<Article> articleEntityList = articleRepository.findAll();

        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if(target != null) {
            articleRepository.save(articleEntity);
        }

        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable long id, RedirectAttributes rttr) {
        log.info("삭제 요청");

        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        if(target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제 완료"); //리다이렉트 시 휘발성 메시지 전송
        }

        return "redirect:/articles";
    }
}
