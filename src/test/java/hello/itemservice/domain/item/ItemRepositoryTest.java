package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest { //junit5에는 public 없어도 됨

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach //test가 끝날 때마다 호출
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item saveItem = itemRepository.save(item); //item 저장

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem); //찾는 아이템이 저장된 아이템과 같은가
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll(); //아이템 모두 찾기
        //return new ArrayList<>(store.values()); 반환을 value들의 list로 함

        //then
        assertThat(result.size()).isEqualTo(2); //총 가져온 아이템 갯수가 2개냐
        assertThat(result).contains(item1, item2); //result가 item1,2를 포함하고 있냐
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();//저장한 item의 id 가져오기

        //when
        Item updateParam = new Item("item2", 20000, 30);//내용 수정하기
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);//update한 아이템의 id 조회

        //각각 항목별로 찾아온 item의 정보와 업데이트 요청한 내용의 정보가 같은지 확인
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
