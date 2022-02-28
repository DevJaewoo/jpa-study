package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "jpashop_seq_generator", sequenceName = "orderitem_seq")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderitem_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
