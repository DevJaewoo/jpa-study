package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "jpashop_seq_generator", sequenceName = "orderitem_seq")
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderitem_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
