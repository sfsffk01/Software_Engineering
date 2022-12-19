package com.mycompany.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String body;
    private LocalDateTime publishedAt;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public void setArticle(Article one) {
    }

    public void setPublishedAt(LocalDateTime now) {
    }

    public void setId(Long replyId) {
    }

    // 省略其他屬性和 getter/setter 方法
}
