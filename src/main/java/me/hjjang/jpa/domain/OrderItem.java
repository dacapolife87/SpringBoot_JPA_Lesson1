package me.hjjang.jpa.domain;

import lombok.Getter;
import lombok.Setter;
import me.hjjang.jpa.domain.item.Item;

import javax.persistence.*;

@Getter @Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;
}
