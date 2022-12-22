package com.mycompany.user;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String title;
    private String body;
    private LocalDateTime publishedAt;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Reply> replies;

    public void setPublishedAt(LocalDateTime now) {
        this.publishedAt = publishedAt;
    }

    public Integer getId() {
        return id;
    }
    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getBody() { return body; }

    public void setId(Integer id) { this.id = id; }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setBody(String body) {
        this.body = body;
    }
}

