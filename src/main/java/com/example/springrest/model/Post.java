package com.example.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")


public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String title;
    @Column(name = "short_text")
    private String shortText;
    @Column
    private String text;
    @Column(name = "created_date")
    private Date date;
    @ManyToOne
    private Category category;
    @Column(name = "pic_url")
    private String picUrl;
    @ManyToOne
    private User user;


}
