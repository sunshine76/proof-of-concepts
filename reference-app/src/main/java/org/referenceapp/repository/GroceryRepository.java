package org.referenceapp.repository;

import org.referenceapp.entity.GroceryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by schinta6 on 12/23/15.
 */
@Repository
public interface GroceryRepository extends JpaRepository<GroceryEntity,Integer> {
    public List<GroceryEntity> findAll();
}

