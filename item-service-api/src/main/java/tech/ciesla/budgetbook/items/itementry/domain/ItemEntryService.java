package tech.ciesla.budgetbook.items.itementry.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.ciesla.budgetbook.common.api.exception.EntityNotFoundException;
import tech.ciesla.budgetbook.items.item.domain.ItemRepository;
import tech.ciesla.budgetbook.items.itementry.api.CreateItemEntryDto;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemEntryService {

    public final ItemRepository itemRepository;
    public final ItemEntryRepository itemEntryRepository;

    public ItemEntry.ItemEntryDetailsList getItemEntryList(Pageable pageable) {
        var totalAmount = itemEntryRepository.getTotalAmount();

        return new ItemEntry.ItemEntryDetailsList(itemEntryRepository.getAll(pageable), totalAmount);
    }

    public ItemEntry.ItemEntryDetailsList getItemEntryListByItemId(UUID itemId, Pageable pageable) {
        var item = itemRepository.getById(itemId);
        if (item.isEmpty()) {
            throw new EntityNotFoundException(
                    UUID.fromString("bb84431e-c60a-4796-8d7e-95b45fb310b1"),
                    String.format("Item with id '%s' not exist", itemId));
        }

        var totalAmount = itemEntryRepository.getTotalAmountByItemId(item.get());

        return new ItemEntry.ItemEntryDetailsList(itemEntryRepository.getAllByItemId(item.get(), pageable), totalAmount);
    }

    public ItemEntry.ItemEntryDetails createItemEntry(CreateItemEntryDto createItemEntryDto) {
        var itemId = createItemEntryDto.getItemId();

        var item = itemRepository.getById(UUID.fromString(itemId));
        if (item.isEmpty()) {
            throw new EntityNotFoundException(
                    UUID.fromString("bb84431e-c60a-4796-8d7e-95b45fb310b1"),
                    String.format("Item with id '%s' not exist", itemId));
        }

        return new ItemEntry.ItemEntryDetails(itemEntryRepository.create(createItemEntryDto.toItemEntry(item.get())));
    }
}
