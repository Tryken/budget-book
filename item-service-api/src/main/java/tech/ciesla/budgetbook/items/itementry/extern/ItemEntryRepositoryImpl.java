package tech.ciesla.budgetbook.items.itementry.extern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.ciesla.budgetbook.items.item.domain.Item;
import tech.ciesla.budgetbook.items.itementry.domain.ItemEntry;
import tech.ciesla.budgetbook.items.itementry.domain.ItemEntryRepository;

import java.util.UUID;

public interface ItemEntryRepositoryImpl extends ItemEntryRepository, CrudRepository<ItemEntry, UUID> {

    Page<ItemEntry> findAll(Pageable pageable);

    @Query("SELECT SUM(ie.amount) FROM ItemEntry ie")
    long countAmount();

    Page<ItemEntry> findByItem(Item item, Pageable pageable);

    @Query("SELECT SUM(ie.amount) FROM ItemEntry ie WHERE ie.item=:item")
    long countAmountByItem(Item item);

    @Override
    default Page<ItemEntry> getAll(Pageable pageable) {
        return this.findAll(pageable);
    }

    @Override
    default long getTotalAmount() {
        return this.countAmount();
    }

    @Override
    default Page<ItemEntry> getAllByItemId(Item item, Pageable pageable) {
        return this.findByItem(item, pageable);
    }

    @Override
    default long getTotalAmountByItemId(Item item) {
        return this.countAmountByItem(item);
    }

    @Override
    default ItemEntry create(ItemEntry itemEntry) {
        return save(itemEntry);
    }
}
