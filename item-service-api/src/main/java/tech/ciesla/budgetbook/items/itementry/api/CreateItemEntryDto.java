package tech.ciesla.budgetbook.items.itementry.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ciesla.budgetbook.items.item.domain.Item;
import tech.ciesla.budgetbook.items.itementry.domain.ItemEntry;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemEntryDto {

    @NotNull(message = "The itemId is required")
    @Size(min = 36, max = 36, message = "The itemId may be 36 characters long")
    String itemId;

    @NotNull(message = "The amount is required")
    int amount;

    public ItemEntry toItemEntry(Item item) {
        var entity = new ItemEntry();
        entity.setItem(item);
        entity.setAmount(getAmount());
        entity.setCreationDateTime(new Date());
        return entity;
    }
}