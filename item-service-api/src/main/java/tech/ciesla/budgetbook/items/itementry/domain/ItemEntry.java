package tech.ciesla.budgetbook.items.itementry.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Page;
import tech.ciesla.budgetbook.items.item.domain.Item;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ItemEntry")
public class ItemEntry {

    @Id
    @Column(name = "Id")
    @Type(type = "uuid-char")
    UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Item_Id")
    private Item item;

    @Column
    private int amount;

    @Column
    Date creationDateTime;

    @Data
    public static class ItemEntryDetails {
        UUID id;
        Item.ItemDetails item;
        int amount;
        Date creationDateTime;

        public ItemEntryDetails(ItemEntry entity) {
            this.id = entity.getId();
            var item = entity.getItem();
            if (item != null) {
                this.item = new Item.ItemDetails(entity.getItem());
            }
            this.amount = entity.getAmount();
            this.creationDateTime = entity.getCreationDateTime();
        }
    }

    @Data
    public static class ItemEntryDetailsList {
        long totalEntries;
        long totalAmount;
        List<ItemEntryDetails> pageEntries;

        public ItemEntryDetailsList(Page<ItemEntry> entityPage, long totalAmount) {
            this.totalEntries = entityPage.getTotalElements();
            this.totalAmount = totalAmount;
            this.pageEntries = entityPage.stream().map(ItemEntryDetails::new).toList();
        }
    }
}
