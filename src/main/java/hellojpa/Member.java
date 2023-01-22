package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //identity : 기본키 생성을 데이터베이스에 위임
    private Long id;

    @Column(name = "name" , nullable = false)
    private String userName;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}