package tech.ciesla.budgetbook.items.item.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository {

    Optional<Item> getById(UUID id);

    Optional<Item> getByName(String name);

    Page<Item> getAll(Pageable pageable);

    Item create(Item item);

    Item update(Item item);

    void delete(UUID id);

    boolean existId(UUID id);

    boolean existName(String name);
}
