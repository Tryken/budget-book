package tech.ciesla.budgetbook.items.item.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import tech.ciesla.budgetbook.items.item.domain.Item;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemDto {

    @Nullable
    @Size(min = 3, message = "The name may be a minimum of 3 characters long")
    @Size(max = 50, message = "The name may be a maximum of 50 characters long")
    String name;

    @Nullable
    @Size(max = 250, message = "The description may be a maximum of 250 characters long")
    String description;

    @Nullable
    @Size(min = 8, message = "The eanCode may be a minimum of 8 characters long")
    @Size(max = 13, message = "The eanCode may be a maximum of 13 characters long")
    String eanCode;

    public Item toItem(UUID id) {
        var entity = new Item();
        entity.setId(id);
        entity.setName(getName());
        entity.setDescription(getDescription());
        entity.setEanCode(getEanCode());
        entity.setLastChangeDateTime(new Date());
        return entity;
    }
}
