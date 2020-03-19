package promn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import promn.dao.model.Review;
import promn.endpoint.dto.common.Result;

/**
 * @author ThinhLH
 * @created 05/03/2019
 * 
 * @modified 05/03/2019
 * @modifier
 */

public interface ReviewService {

	/**
	 * 
	 * @param pageable
	 * @return a {@link Page} of review
	 */
	Page<Review> getPage(Pageable pageable);

	/**
	 * 
	 * @param pageable
	 * @param review:  the search object
	 * @return a {@link Page} of review
	 */
	Page<Review> getPage(Pageable pageable, Review review);

	/**
	 * 
	 * @param pageable
	 * @param filter: the search keywords
	 * @return a {@link Page} of review
	 */
	Page<Review> getPage(Pageable pageable, String filter);

	/**
	 * 
	 * @return a {@link List} of review
	 */
	List<Review> getList();

	/**
	 * 
	 * @param id: the id of the review
	 * @return a {@link Review}
	 */
	Review getOne(int id);

	/**
	 * create a new review
	 * @param review: the new review
	 * @return a {@link Result}
	 */
	Result create(Review review);

	/**
	 * update infomation of the review
	 * @param review: the new review
	 * @return a {@link Result}
	 */
	Result update(Review review);

	/**
	 * 
	 * @param id: the id of the review
	 * @return  a {@link Result}
	 */
	Result delete(int id);

	/**
	 * delete a list review
	 * @param ids: the list id
	 * @return a {@link Result}
	 */
	Result delete(int[] ids);
}
