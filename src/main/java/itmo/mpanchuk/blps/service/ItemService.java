package itmo.mpanchuk.blps.service;

import itmo.mpanchuk.blps.model.Item;
import itmo.mpanchuk.blps.repo.ItemTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemTestRepository itemTestRepository;

    @Autowired
    public ItemService(ItemTestRepository itemTestRepository) {
        this.itemTestRepository = itemTestRepository;
    }

    public Page<Item> getAllItems(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return itemTestRepository.findAll(pageable);
    }

    public Page<Item> getItemByRegexp(String regexp, int pageNo, int pageSize) {
        return itemTestRepository.findByNameLike(regexp, PageRequest.of(pageNo, pageSize));
    }

    public Item getItemById(Long id) {
        return itemTestRepository.findById(id).orElse(null);
    }

    public Item saveItem(Item item) {
        return itemTestRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemTestRepository.deleteById(id);
    }
}
