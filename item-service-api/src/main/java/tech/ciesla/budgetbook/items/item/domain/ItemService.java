package tech.ciesla.budgetbook.items.item.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.ciesla.budgetbook.common.api.exception.EntityNotFoundException;
import tech.ciesla.budgetbook.common.api.exception.UniqueAttributeException;
import tech.ciesla.budgetbook.items.item.api.CreateItemDto;
import tech.ciesla.budgetbook.items.item.api.UpdateItemDto;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {

    public final ItemRepository itemRepository;

    public Item.ItemDetails getItem(UUID id) {
        var itemOptional = itemRepository.getById(id);

        if (itemOptional.isPresent()) {
            return new Item.ItemDetails(itemOptional.get());
        }

        throw new EntityNotFoundException(
                UUID.fromString("aba43709-b615-4f82-9bf5-469a126875ea"),
                String.format("Item with the id '%s' could not be found", id));
    }

    public Item.ItemDetailsList getItemList(Pageable pageable) {
        return new Item.ItemDetailsList(itemRepository.getAll(pageable));
    }

    public Item.ItemDetails createItem(CreateItemDto createItemDto) {
        /*if (!createItemDto.getEanCode().isEmpty()) {
            try {
                var document = Jsoup.connect("https://opengtindb.org/index.php")
                        .data("cmd", "ean1")
                        .data("ean", createItemDto.getEanCode())
                        .post();
                var fullName = document.getElementsByAttributeValue("name", "fullname").get(0).attributes().get("value");
                var description = document.getElementsByAttributeValue("name", "descr").get(0).attributes().get("value");
                createItemDto.setName(fullName);
                createItemDto.setDescription(description);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/

        var name = createItemDto.getName();
        if (itemRepository.existName(name)) {
            throw new UniqueAttributeException(
                    UUID.fromString("a5b236e4-6535-4296-81d0-f3b452dc240f"),
                    String.format("Item with name '%s' already exist", name));
        }

        return new Item.ItemDetails(itemRepository.create(createItemDto.toItem()));
    }

    public Item.ItemDetails updateItem(UUID id, UpdateItemDto updateItemDto) {
        var itemOptional = itemRepository.getById(id);

        if (itemOptional.isEmpty()) {
            throw new EntityNotFoundException(
                    UUID.fromString("277f9fe3-4c44-40d0-ad56-6e13489fbb95"),
                    String.format("Item with the id '%s' could not be found", id));
        }

        var itemByName = itemRepository.getByName(updateItemDto.getName());
        var name = updateItemDto.getName();
        if (name != null && itemByName.isPresent() && itemByName.get().getId() != id) {
            throw new UniqueAttributeException(
                    UUID.fromString("a875ceae-8b7d-421b-9976-dba05063e303"),
                    String.format("Item with name '%s' already exist", name));
        }

        return new Item.ItemDetails(itemRepository.update(updateItemDto.toItem(id)));
    }

    public void deleteItem(UUID id) {
        var itemOptional = itemRepository.getById(id);

        if (itemOptional.isEmpty()) {
            throw new EntityNotFoundException(
                    UUID.fromString("d44b294d-4367-4225-ab7f-3a34ba209238"),
                    String.format("Item with the id '%s' could not be found", id));
        }

        itemRepository.delete(id);
    }
}
