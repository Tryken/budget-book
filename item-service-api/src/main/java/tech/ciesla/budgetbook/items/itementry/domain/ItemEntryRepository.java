package tech.ciesla.budgetbook.items.itementry.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tech.ciesla.budgetbook.items.item.domain.Item;

@Repository
public interface ItemEntryRepository {

    Page<ItemEntry> getAll(Pageable pageable);

    long getTotalAmount();

    Page<ItemEntry> getAllByItemId(Item item, Pageable pageable);

    long getTotalAmountByItemId(Item item);

    ItemEntry create(ItemEntry itemEntry);
}
