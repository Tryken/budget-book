package tech.ciesla.budgetbook.items.item.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.ciesla.budgetbook.items.item.domain.Item;
import tech.ciesla.budgetbook.items.item.domain.ItemService;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("{id}")
    public Item.ItemDetails getItem(@PathVariable UUID id) {
        return itemService.getItem(id);
    }

    @GetMapping()
    public Item.ItemDetailsList getItems(
            @SortDefault(sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return itemService.getItemList(pageable);
    }

    @PostMapping()
    public Item.ItemDetails createItem(@Valid @RequestBody CreateItemDto createItemDto) {
        return itemService.createItem(createItemDto);
    }

    @PutMapping("{id}")
    public Item.ItemDetails updateItem(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateItemDto updateItemDto
    ) {
        return itemService.updateItem(id, updateItemDto);
    }

    @DeleteMapping("{id}")
    public void updateItem(@PathVariable UUID id) {
        itemService.deleteItem(id);
    }
}
