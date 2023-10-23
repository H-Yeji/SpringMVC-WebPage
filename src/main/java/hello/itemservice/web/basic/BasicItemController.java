package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) { //item 목록 출력
        List<Item> items = itemRepository.findAll(); //모든 아이템 목록 뿌리기
        model.addAttribute("items", items); //model에 넣기
        return "basic/items"; //뷰 템플릿 호출
    }

    /**
     *상품 상세 컨트롤러
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item"; //뷰템플릿 파일 경로
    }

    /**
     * 상품 등록 폼 -> 목록에서 상품 목록 클릭
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * 상품 등록 폼에서 상품 등록 버튼 클릭
     */
//    @PostMapping("/add") //RequestParam을 이용해 데이터 넘겨오기 (save메서드가 이거였음)
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item"; //item을 등록하면 상세 페이지로 보여주기
    }
    //같은 url인 /add로 들어오더라도, get으로 들어오면 addForm이, post로 들어오면 save가 호출됨

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);
        //modelAttriute("item")이 자동으로 model에 넣어주는 역할까지 함 -> model 파라미터도 없어도 됨
        //model.addAttribute("item", item);

        return "basic/item"; //item을 등록하면 상세 페이지로 보여주기
    }

    /**
     * @ModelAttribute name 생략 가능
     * model.addAttribute(item); 자동 추가, 생략 가능
     * 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * @ModelAttribute 자체 생략 가능
     * model.addAttribute(item) 자동 추가
     */
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() { //test용으로 아이템 두개 넣어두기
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
