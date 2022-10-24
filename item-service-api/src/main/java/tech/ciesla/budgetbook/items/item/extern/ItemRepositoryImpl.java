package tech.ciesla.budgetbook.items.item.extern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tech.ciesla.budgetbook.items.item.domain.Item;
import tech.ciesla.budgetbook.items.item.domain.ItemRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepositoryImpl extends ItemRepository, CrudRepository<Item, UUID> {

    Page<Item> findAll(Pageable pageable);

    Optional<Item> findByName(String name);


    boolean existsById(UUID id);

    @Override
    default Optional<Item> getById(UUID id) {
        return findById(id);
    }

    @Override
    default Optional<Item> getByName(String name) {
        return findByName(name);
    }

    @Override
    default Page<Item> getAll(Pageable pageable) {
        return this.findAll(pageable);
    }

    @Override
    default Item create(Item item) {
        return save(item);
    }

    @Override
    default Item update(Item item) {
        return save(item);
    }

    @Override
    default void delete(UUID id) {
        deleteById(id);
    }

    @Override
    default boolean existId(UUID id) {
        return existsById(id);
    }

    @Override
    default boolean existName(String name) {
        return findByName(name).isPresent();
    }
}
