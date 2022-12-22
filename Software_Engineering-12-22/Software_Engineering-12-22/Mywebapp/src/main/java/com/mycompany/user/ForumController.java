package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ForumController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/article")
    public String listArticles(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "article_list";
    }

    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable Integer id, Model model) {
        Article article = articleRepository.getById(id);
        model.addAttribute("article", article);
        return "article_view";
    }

    @GetMapping("/article/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article_form";
    }

    @PostMapping("/article/new")
    public String createArticle(Article article) {
        article.setPublishedAt(LocalDateTime.now());
        articleRepository.save(article);
        return "redirect:/article/";
    }
    /*@GetMapping("/article/{id}/edit")
    public String editArticle(@PathVariable Integer id, Model model) {
        Article article = articleRepository.getById(id);
        model.addAttribute("article", article);
        return "article_form";
    }
    @PostMapping("/article/{id}/edit")
    public String updateArticle(@PathVariable Integer id, Article article) {
        article.setId(id);
        articleRepository.save(article);
        return "redirect:/article/" + article.getId();
    }

    @GetMapping("/article/{id}/delete")
    public String deleteArticle(@PathVariable Integer id) {
        articleRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/article/{id}/reply/new")
    public String newReply(@PathVariable Long id, Model model) {
        model.addAttribute("reply", new Reply());
        model.addAttribute("articleId", id);
        return "reply_form";
    }

    @PostMapping("/article/{id}/reply/new")
    public String createReply(@PathVariable Integer id, Reply reply) {
        reply.setArticle(articleRepository.getById(id));
        reply.setPublishedAt(LocalDateTime.now());
        replyRepository.save(reply);
        return "redirect:/article/" + id;
    }

    @GetMapping("/article/{articleId}/reply/{replyId}/edit")
    public String editReply(@PathVariable Long articleId, @PathVariable Long replyId, Model model) {
        Reply reply = replyRepository.getOne(replyId);
        model.addAttribute("reply", reply);
        model.addAttribute("articleId", articleId);
        return "reply_form";
    }

    @PostMapping("/article/{articleId}/reply/{replyId}/edit")
    public String updateReply(@PathVariable Long articleId, @PathVariable Long replyId, Reply reply) {
        reply.setId(replyId);
        replyRepository.save(reply);
        return "redirect:/article/" + articleId;
    }

    @GetMapping("/article/{articleId}/reply/{replyId}/delete")
    public String deleteReply(@PathVariable Long articleId, @PathVariable Long replyId) {
        replyRepository.deleteById(replyId);
        return "redirect:/article/" + articleId;
    }*/
}