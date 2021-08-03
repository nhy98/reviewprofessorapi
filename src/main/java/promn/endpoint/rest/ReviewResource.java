package promn.endpoint.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import promn.dao.model.Review;
import promn.endpoint.dto.common.JsonHelper;
import promn.endpoint.dto.common.ResponseData;
import promn.endpoint.dto.common.Result;
import promn.service.ReviewService;

@Controller
@RequestMapping("/8888group/reviews")
public class ReviewResource {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Page<Review>>> getPage(Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "filter", required = false) String filter) {
		ResponseData<Page<Review>> response = new ResponseData<>();
		Page<Review> pageDataSourceType = null;
		Review searchObject = JsonHelper.jsonToObject(search, Review.class);

		// advance search
		if (searchObject != null) {
			pageDataSourceType = reviewService.getPage(pageable, searchObject);
		}
		// filter search
		else if (filter != null) {
			pageDataSourceType = reviewService.getPage(pageable, filter);
		}
		// get page default
		else {
			pageDataSourceType = reviewService.getPage(pageable);
		}

		if (pageDataSourceType != null) {
			response = new ResponseData<Page<Review>>(pageDataSourceType, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Page<Review>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Page<Review>>(pageDataSourceType, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Page<Review>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<List<Review>>> getList() {
		ResponseData<List<Review>> response = new ResponseData<>();
		List<Review> listDataSourceTypes = null;
		listDataSourceTypes = reviewService.getList();
		if (listDataSourceTypes != null) {
			response = new ResponseData<List<Review>>(listDataSourceTypes, Result.SUCCESS);
			return new ResponseEntity<ResponseData<List<Review>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<List<Review>>(listDataSourceTypes, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<List<Review>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Review>> findOne(@PathVariable("id") int apiId) {
		ResponseData<Review> response = new ResponseData<Review>();
		Review review = reviewService.getOne(apiId);
		if (review != null) {
			response = new ResponseData<Review>(review, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Review>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Review>(review, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Review>>(response, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> create(@RequestBody Review review) {
		Result result = reviewService.create(review);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<Integer>> update(@RequestBody Review review) {
		Result result = reviewService.update(review);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> delete(@PathVariable("id") Integer id) {
		Result result = reviewService.delete(id);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> deleteAllBatch(@RequestParam("entityIds") int[] entityIds) {
		Result result = reviewService.delete(entityIds);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}
}

