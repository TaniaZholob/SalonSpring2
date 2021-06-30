package com.tania.zholob.demo.model.services;

import com.tania.zholob.demo.model.entity.Reviews;

public interface ReviewService {
    Reviews saveReview(Reviews review, Long id, Long idUser);
}
