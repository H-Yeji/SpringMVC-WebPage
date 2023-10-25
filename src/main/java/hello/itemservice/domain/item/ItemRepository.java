package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //내부에 @Component가 있음 -> component scan의 대상이 됨
public class ItemRepository {

    //static 사용했다는 점 주의
    private static final Map<Long, Item> store = new HashMap<>(); //item의 id가 long 타입이라 맞춰줌
    private static long sequence = 0L;

    public Item save(Item item) { //아이템 저장소
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);//item 찾기
        findItem.setItemName(updateParam.getItemName()); //파라미터 정보 넘어옴
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void delete(Long itemId) {
        store.remove(itemId);
    }

    public void clearStore() {
        store.clear();
    }
}
