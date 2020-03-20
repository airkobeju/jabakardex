package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.ItemBoletaDetail;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemBoletaDetailRepository<T extends ItemBoletaDetail> extends MongoRepository<T, String> {

}