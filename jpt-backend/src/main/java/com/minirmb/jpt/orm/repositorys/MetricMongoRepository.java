package com.minirmb.jpt.orm.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.minirmb.jpt.orm.entity.Metric;

public interface MetricMongoRepository extends MongoRepository<Metric,Long>{
}
