package hellojpa;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "member_seq_generator",
        sequenceName = "member_sequence",
        initialValue = 1, allocationSize = 50)
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence")
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
