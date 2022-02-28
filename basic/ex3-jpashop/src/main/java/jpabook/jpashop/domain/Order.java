package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "jpashop_seq_generator", sequenceName = "order_seq")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
