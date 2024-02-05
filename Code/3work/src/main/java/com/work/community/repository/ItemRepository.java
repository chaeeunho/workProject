package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
