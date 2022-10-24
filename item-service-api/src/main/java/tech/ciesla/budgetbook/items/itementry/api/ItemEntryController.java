package tech.ciesla.budgetbook.items.itementry.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.ciesla.budgetbook.items.itementry.domain.ItemEntry;
import tech.ciesla.budgetbook.items.itementry.domain.ItemEntryService;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/itementry")
public class ItemEntryController {

    private final ItemEntryService itemEntryService;

    @GetMapping()
    public ItemEntry.ItemEntryDetailsList getItemEntryList(
            @SortDefault(sort = "creationDateTime", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return itemEntryService.getItemEntryList(pageable);
    }

    @GetMapping("{itemId}")
    public ItemEntry.ItemEntryDetailsList getItemEntryListByItemId(
            @PathVariable UUID itemId,
            @SortDefault(sort = "creationDateTime", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return itemEntryService.getItemEntryListByItemId(itemId, pageable);
    }

    @PostMapping()
    public ItemEntry.ItemEntryDetails createItemEntry(@Valid @RequestBody CreateItemEntryDto createItemEntryDto) {
        return itemEntryService.createItemEntry(createItemEntryDto);
    }
}
