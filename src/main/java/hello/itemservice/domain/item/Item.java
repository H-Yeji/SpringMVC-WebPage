package hello.itemservice.domain.item;

import lombok.Data;

@Data //data를 쓰려면 내부를 다 알고 써야함 (주의해서)
public class Item {

    private Long id;
    private String itemName;
    private Integer price; //price가 안들어갈 때도 있다고 가정해서 Integer
    private Integer quantity;

    public Item() { //기본 생성자
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
