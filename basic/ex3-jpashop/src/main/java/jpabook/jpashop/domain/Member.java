package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "jpashop_seq_generator", sequenceName = "member_seq")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
