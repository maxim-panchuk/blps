package itmo.mpanchuk.blps.controller;


import itmo.mpanchuk.blps.model.Item;
import itmo.mpanchuk.blps.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {this.itemService = itemService;}

    @GetMapping
    public Page<Item> getAllItems(@RequestParam(defaultValue = "0") int pageNo,
                                  @RequestParam(defaultValue = "10") int pageSize) {
        return itemService.getAllItems(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/find")
    public Page<Item> getItemByName(@RequestParam(defaultValue = "0") int pageNo,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam String regexp) {
        logger.info("Getting item with regexp: " + regexp);
        return itemService.getItemByRegexp(regexp, pageNo, pageSize);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item savedItem = itemService.saveItem(item);
        logger.info("Creating item: " + item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
//        itemService.deleteItem(id);
//        logger.info("Deleting item with id: " + id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
