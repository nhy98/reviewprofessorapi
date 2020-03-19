package promn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import promn.dao.model.Review;
import promn.dao.repository.ReviewRepository;
import promn.dao.specifications.ReviewSpecifications;
import promn.endpoint.dto.common.Result;
import promn.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;

	private Logger log = Logger.getLogger(ReviewServiceImpl.class);

	@Override
	public Page<Review> getPage(Pageable pageable) {
		Page<Review> reviewPage = null;
		try {
			reviewPage = reviewRepository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reviewPage;
	}

	@Override
	public Page<Review> getPage(Pageable pageable, Review review) {
		Page<Review> reviewPage = null;
		try {
			reviewPage = reviewRepository.findAll(ReviewSpecifications.advanceSearch(review), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reviewPage;
	}

	@Override
	public Page<Review> getPage(Pageable pageable, String filter) {
		Page<Review> reviewPage = null;
		try {
			reviewPage = reviewRepository.findAll(ReviewSpecifications.filterSearch(filter), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reviewPage;
	}

	@Override
	public List<Review> getList() {
		List<Review> reviewList = null;
		try {
			reviewList = reviewRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reviewList;
	}

	@Override
	public Review getOne(int id) {
		Review review = null;
		try {
			review = reviewRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return review;
	}

	@Override
	public Result create(Review review) {
		try {
			reviewRepository.save(review);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result update(Review review) {
		try {
			reviewRepository.save(review);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result delete(int id) {
		try {
			reviewRepository.deleteById(id);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	@Transactional
	public Result delete(int[] ids) {
		try {
			List<Review> lReviews = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				lReviews.add(reviewRepository.findById(ids[i]));
			}
			reviewRepository.deleteAll(lReviews);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}
}

