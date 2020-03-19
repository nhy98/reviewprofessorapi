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

import promn.dao.model.Clazz;
import promn.endpoint.dto.common.JsonHelper;
import promn.endpoint.dto.common.ResponseData;
import promn.endpoint.dto.common.Result;
import promn.service.ClassService;

/**
 * @author ThinhLH
 * @created 05/03/2019
 * 
 * @modified 05/03/2019
 * @modifier
 */
@Controller
@RequestMapping("/8888group/classes")
public class ClassResource {
	@Autowired
	private ClassService clazzService;
	
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Page<Clazz>>> getPage(Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "filter", required = false) String filter) {
		ResponseData<Page<Clazz>> response = new ResponseData<>();
		Page<Clazz> pageDataSourceType = null;
		Clazz searchObject = JsonHelper.jsonToObject(search, Clazz.class);

		// advance search
		if (searchObject != null) {
			pageDataSourceType = clazzService.getPage(pageable, searchObject);
		}
		// filter search
		else if (filter != null) {
			pageDataSourceType = clazzService.getPage(pageable, filter);
		}
		// get page default
		else {
			pageDataSourceType = clazzService.getPage(pageable);
		}

		if (pageDataSourceType != null) {
			response = new ResponseData<Page<Clazz>>(pageDataSourceType, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Page<Clazz>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Page<Clazz>>(pageDataSourceType, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Page<Clazz>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<List<Clazz>>> getList() {
		ResponseData<List<Clazz>> response = new ResponseData<>();
		List<Clazz> listDataSourceTypes = null;
		listDataSourceTypes = clazzService.getList();
		if (listDataSourceTypes != null) {
			response = new ResponseData<List<Clazz>>(listDataSourceTypes, Result.SUCCESS);
			return new ResponseEntity<ResponseData<List<Clazz>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<List<Clazz>>(listDataSourceTypes, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<List<Clazz>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Clazz>> findOne(@PathVariable("id") int apiId) {
		ResponseData<Clazz> response = new ResponseData<Clazz>();
		Clazz clazz = clazzService.getOne(apiId);
		if (clazz != null) {
			response = new ResponseData<Clazz>(clazz, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Clazz>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Clazz>(clazz, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Clazz>>(response, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> create(@RequestBody Clazz clazz) {
		Result result = clazzService.create(clazz);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<Integer>> update(@RequestBody Clazz clazz) {
		Result result = clazzService.update(clazz);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> delete(@PathVariable("id") Integer id) {
		Result result = clazzService.delete(id);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> deleteAllBatch(@RequestParam("entityIds") int[] entityIds) {
		Result result = clazzService.delete(entityIds);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}
}

