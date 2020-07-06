package cl.transbank.restaurant_api.repository;

import cl.transbank.restaurant_api.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {

    @Query("select s from Sale s where s.saleDate = :salesDay")
    List<Sale> findSalesOfTheDay( @Param("salesDay") Date now);
}
