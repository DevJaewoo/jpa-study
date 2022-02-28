package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "jpashop_seq_generator", sequenceName = "item_seq")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
