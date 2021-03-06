package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "jpashop_seq_generator", sequenceName = "delivery_seq")
public class Delivery extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
