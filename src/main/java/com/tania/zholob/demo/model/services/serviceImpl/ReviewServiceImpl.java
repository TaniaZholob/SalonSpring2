package com.tania.zholob.demo.model.services.serviceImpl;

import com.tania.zholob.demo.model.entity.Reviews;
import com.tania.zholob.demo.model.repos.MasterRepo;
import com.tania.zholob.demo.model.repos.ReviewRepo;
import com.tania.zholob.demo.model.services.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final MasterRepo masterRepo;

    public ReviewServiceImpl(ReviewRepo reviewRepo, MasterRepo masterRepo) {
        this.reviewRepo = reviewRepo;
        this.masterRepo = masterRepo;
    }


    @Override
    @Transactional
    public Reviews saveReview(Reviews review, Long id, Long idUser) {
        review.setMaster_id(id);
        review.setUser_id(idUser);
        Reviews r = reviewRepo.save(review);
        masterRepo.updateMasterRating(id, reviewRepo.avg(id));
        return r;
    }
}
