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

import promn.dao.model.Subject;
import promn.endpoint.dto.common.JsonHelper;
import promn.endpoint.dto.common.ResponseData;
import promn.endpoint.dto.common.Result;
import promn.service.SubjectService;

@Controller
@RequestMapping("/8888group/subjects")
public class SubjectResource {
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Page<Subject>>> getPage(Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "filter", required = false) String filter) {
		ResponseData<Page<Subject>> response = new ResponseData<>();
		Page<Subject> pageDataSourceType = null;
		Subject searchObject = JsonHelper.jsonToObject(search, Subject.class);

		// advance search
		if (searchObject != null) {
			pageDataSourceType = subjectService.getPage(pageable, searchObject);
		}
		// filter search
		else if (filter != null) {
			pageDataSourceType = subjectService.getPage(pageable, filter);
		}
		// get page default
		else {
			pageDataSourceType = subjectService.getPage(pageable);
		}

		if (pageDataSourceType != null) {
			response = new ResponseData<Page<Subject>>(pageDataSourceType, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Page<Subject>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Page<Subject>>(pageDataSourceType, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Page<Subject>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<List<Subject>>> getList() {
		ResponseData<List<Subject>> response = new ResponseData<>();
		List<Subject> listDataSourceTypes = null;
		listDataSourceTypes = subjectService.getList();
		if (listDataSourceTypes != null) {
			response = new ResponseData<List<Subject>>(listDataSourceTypes, Result.SUCCESS);
			return new ResponseEntity<ResponseData<List<Subject>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<List<Subject>>(listDataSourceTypes, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<List<Subject>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Subject>> findOne(@PathVariable("id") int apiId) {
		ResponseData<Subject> response = new ResponseData<Subject>();
		Subject subject = subjectService.getOne(apiId);
		if (subject != null) {
			response = new ResponseData<Subject>(subject, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Subject>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Subject>(subject, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Subject>>(response, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> create(@RequestBody Subject subject) {
		Result result = subjectService.create(subject);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<Integer>> update(@RequestBody Subject subject) {
		Result result = subjectService.update(subject);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> delete(@PathVariable("id") Integer id) {
		Result result = subjectService.delete(id);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> deleteAllBatch(@RequestParam("entityIds") int[] entityIds) {
		Result result = subjectService.delete(entityIds);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}
}

