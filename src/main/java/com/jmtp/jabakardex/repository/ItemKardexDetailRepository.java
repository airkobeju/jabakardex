package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.ItemKardexDetail;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemKardexDetailRepository extends MongoRepository<ItemKardexDetail, String> {

}