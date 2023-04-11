package itmo.mpanchuk.blps.repo;

import itmo.mpanchuk.blps.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTestRepository extends JpaRepository<Item, Long> {
    Page<Item> findAll(Pageable pageable);

    @Query("SELECT i FROM Item i WHERE lower(i.name) LIKE lower(concat('%', :regexp, '%'))")
    Page<Item> findByNameLike(@Param("regexp") String regexp, Pageable pageable);
}
