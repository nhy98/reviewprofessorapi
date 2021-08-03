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

import promn.dao.model.User;
import promn.endpoint.dto.common.JsonHelper;
import promn.endpoint.dto.common.ResponseData;
import promn.endpoint.dto.common.Result;
import promn.service.UserService;


@Controller
@RequestMapping("/8888group/users")
public class UserResource {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Page<User>>> getPage(Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "filter", required = false) String filter) {
		ResponseData<Page<User>> response = new ResponseData<>();
		Page<User> pageDataSourceType = null;
		User searchObject = JsonHelper.jsonToObject(search, User.class);

		// advance search
		if (searchObject != null) {
			pageDataSourceType = userService.getPage(pageable, searchObject);
		}
		// filter search
		else if (filter != null) {
			pageDataSourceType = userService.getPage(pageable, filter);
		}
		// get page default
		else {
			pageDataSourceType = userService.getPage(pageable);
		}

		if (pageDataSourceType != null) {
			response = new ResponseData<Page<User>>(pageDataSourceType, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Page<User>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Page<User>>(pageDataSourceType, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Page<User>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<List<User>>> getList() {
		ResponseData<List<User>> response = new ResponseData<>();
		List<User> listDataSourceTypes = null;
		listDataSourceTypes = userService.getList();
		if (listDataSourceTypes != null) {
			response = new ResponseData<List<User>>(listDataSourceTypes, Result.SUCCESS);
			return new ResponseEntity<ResponseData<List<User>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<List<User>>(listDataSourceTypes, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<List<User>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<User>> findOne(@PathVariable("id") int apiId) {
		ResponseData<User> response = new ResponseData<User>();
		User user = userService.getOne(apiId);
		if (user != null) {
			response = new ResponseData<User>(user, Result.SUCCESS);
			return new ResponseEntity<ResponseData<User>>(response, HttpStatus.OK);
		}
		response = new ResponseData<User>(user, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<User>>(response, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> create(@RequestBody User user) {
		Result result = userService.create(user);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<Integer>> update(@RequestBody User user) {
		Result result = userService.update(user);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> delete(@PathVariable("id") Integer id) {
		Result result = userService.delete(id);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> deleteAllBatch(@RequestParam("entityIds") int[] entityIds) {
		Result result = userService.delete(entityIds);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}
}

