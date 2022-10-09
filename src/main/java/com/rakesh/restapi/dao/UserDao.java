package com.rakesh.restapi.dao;

import javax.persistence.*;

@Entity
@Table(name = "userdao")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Integer id;
    private String userName;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumeId")
    private ResumeFileDao resumeFileDao;

    public String getUserName() {
        return userName;
    }

    public Integer getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResumeFileDao getResumeFileDao() {
        return resumeFileDao;
    }

    public void setResumeFileDao(ResumeFileDao resumeFileDao) {
        this.resumeFileDao = resumeFileDao;
    }
}
