package tech.ciesla.budgetbook.items.item.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "Id")
    @Type(type = "uuid-char")
    UUID id = UUID.randomUUID();

    @Column(name = "Name", length = 50, nullable = false, unique = true)
    String name;

    @Column(name = "Description", length = 250)
    String description;

    @Column(name = "Ean_Code", length = 13)
    String eanCode;

    @Column
    Date creationDateTime;

    @Column
    Date lastChangeDateTime;

    @Data
    public static class ItemDetails {
        UUID id;
        String name;
        String description;
        String eanCode;
        Date creationDateTime;
        Date lastChangeDateTime;

        public ItemDetails(Item entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.description = entity.getDescription();
            this.eanCode = entity.getEanCode();
            this.creationDateTime = entity.creationDateTime;
            this.lastChangeDateTime = entity.getLastChangeDateTime();
        }
    }

    @Data
    public static class ItemDetailsList {
        long totalEntries;
        List<ItemDetails> pageEntries;

        public ItemDetailsList(Page<Item> entityPage) {
            this.totalEntries = entityPage.getTotalElements();
            this.pageEntries = entityPage.stream().map(ItemDetails::new).toList();
        }
    }
}
