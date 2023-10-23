package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @PostMapping("/add") //addForm과 같은 url로 오더라도 get, post의 차이로 구분
    public String save() {
        return "xxx";
    }
    //같은 url인 /add로 들어오더라도, get으로 들어오면 addForm이, post로 들어오면 save가 호출됨

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() { //test용으로 아이템 두개 넣어두기
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
