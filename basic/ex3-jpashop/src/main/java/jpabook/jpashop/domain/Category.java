package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "jpashop_seq_generator", sequenceName = "category_seq")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child;
}
