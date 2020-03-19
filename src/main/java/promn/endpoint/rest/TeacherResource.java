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

import promn.dao.model.Teacher;
import promn.endpoint.dto.common.JsonHelper;
import promn.endpoint.dto.common.ResponseData;
import promn.endpoint.dto.common.Result;
import promn.service.TeacherService;

/**
 * @author ThinhLH
 * @created 05/03/2019
 * 
 * @modified 05/03/2019
 * @modifier
 */
@Controller
@RequestMapping("/8888group/teachers")
public class TeacherResource {
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Page<Teacher>>> getPage(Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "filter", required = false) String filter) {
		ResponseData<Page<Teacher>> response = new ResponseData<>();
		Page<Teacher> pageDataSourceType = null;
		Teacher searchObject = JsonHelper.jsonToObject(search, Teacher.class);

		// advance search
		if (searchObject != null) {
			pageDataSourceType = teacherService.getPage(pageable, searchObject);
		}
		// filter search
		else if (filter != null) {
			pageDataSourceType = teacherService.getPage(pageable, filter);
		}
		// get page default
		else {
			pageDataSourceType = teacherService.getPage(pageable);
		}

		if (pageDataSourceType != null) {
			response = new ResponseData<Page<Teacher>>(pageDataSourceType, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Page<Teacher>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Page<Teacher>>(pageDataSourceType, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Page<Teacher>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<List<Teacher>>> getList() {
		ResponseData<List<Teacher>> response = new ResponseData<>();
		List<Teacher> listDataSourceTypes = null;
		listDataSourceTypes = teacherService.getList();
		if (listDataSourceTypes != null) {
			response = new ResponseData<List<Teacher>>(listDataSourceTypes, Result.SUCCESS);
			return new ResponseEntity<ResponseData<List<Teacher>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<List<Teacher>>(listDataSourceTypes, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<List<Teacher>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Teacher>> findOne(@PathVariable("id") int apiId) {
		ResponseData<Teacher> response = new ResponseData<Teacher>();
		Teacher teacher = teacherService.getOne(apiId);
		if (teacher != null) {
			response = new ResponseData<Teacher>(teacher, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Teacher>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Teacher>(teacher, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Teacher>>(response, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> create(@RequestBody Teacher teacher) {
		Result result = teacherService.create(teacher);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<Integer>> update(@RequestBody Teacher teacher) {
		Result result = teacherService.update(teacher);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> delete(@PathVariable("id") Integer id) {
		Result result = teacherService.delete(id);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> deleteAllBatch(@RequestParam("entityIds") int[] entityIds) {
		Result result = teacherService.delete(entityIds);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}
}

