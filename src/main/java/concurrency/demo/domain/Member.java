package concurrency.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    public Member(String email) {
        this.email = email;
    }

    protected Member() {
    }

    public boolean isSameMember(String email) {
        return this.email.equals(email);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
