package tech.ciesla.budgetbook.items.item.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ciesla.budgetbook.items.item.domain.Item;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemDto {

    @NotNull(message = "The name is required")
    @Size(min = 3, message = "The name may be a minimum of 3 characters long")
    @Size(max = 50, message = "The name may be a maximum of 50 characters long")
    String name;

    @Size(max = 250, message = "The description may be a maximum of 250 characters long")
    String description;

    @Size(max = 13, message = "The eanCode may be a maximum of 13 characters long")
    String eanCode;

    public Item toItem() {
        var entity = new Item();
        entity.setName(getName());
        entity.setDescription(getDescription());
        entity.setEanCode(getEanCode());
        entity.setCreationDateTime(new Date());
        entity.setLastChangeDateTime(new Date());
        return entity;
    }
}
