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

import promn.dao.model.Report;
import promn.endpoint.dto.common.JsonHelper;
import promn.endpoint.dto.common.ResponseData;
import promn.endpoint.dto.common.Result;
import promn.service.ReportService;

@Controller
@RequestMapping("/8888group/reports")
public class ReportResource {
	@Autowired
	private ReportService reportService;
	
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Page<Report>>> getPage(Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "filter", required = false) String filter) {
		ResponseData<Page<Report>> response = new ResponseData<>();
		Page<Report> pageDataSourceType = null;
		Report searchObject = JsonHelper.jsonToObject(search, Report.class);

		// advance search
		if (searchObject != null) {
			pageDataSourceType = reportService.getPage(pageable, searchObject);
		}
		// filter search
		else if (filter != null) {
			pageDataSourceType = reportService.getPage(pageable, filter);
		}
		// get page default
		else {
			pageDataSourceType = reportService.getPage(pageable);
		}

		if (pageDataSourceType != null) {
			response = new ResponseData<Page<Report>>(pageDataSourceType, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Page<Report>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Page<Report>>(pageDataSourceType, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Page<Report>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<List<Report>>> getList() {
		ResponseData<List<Report>> response = new ResponseData<>();
		List<Report> listDataSourceTypes = null;
		listDataSourceTypes = reportService.getList();
		if (listDataSourceTypes != null) {
			response = new ResponseData<List<Report>>(listDataSourceTypes, Result.SUCCESS);
			return new ResponseEntity<ResponseData<List<Report>>>(response, HttpStatus.OK);
		}
		response = new ResponseData<List<Report>>(listDataSourceTypes, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<List<Report>>>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseData<Report>> findOne(@PathVariable("id") int apiId) {
		ResponseData<Report> response = new ResponseData<Report>();
		Report report = reportService.getOne(apiId);
		if (report != null) {
			response = new ResponseData<Report>(report, Result.SUCCESS);
			return new ResponseEntity<ResponseData<Report>>(response, HttpStatus.OK);
		}
		response = new ResponseData<Report>(report, Result.NO_CONTENT);
		return new ResponseEntity<ResponseData<Report>>(response, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResponseData<Integer>> create(@RequestBody Report report) {
		Result result = reportService.create(report);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<Integer>> update(@RequestBody Report report) {
		Result result = reportService.update(report);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.CREATED);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> delete(@PathVariable("id") Integer id) {
		Result result = reportService.delete(id);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Integer>> deleteAllBatch(@RequestParam("entityIds") int[] entityIds) {
		Result result = reportService.delete(entityIds);
		if (result.isSuccess()) {
			return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseData<Integer>>(new ResponseData<>(result), HttpStatus.BAD_REQUEST);
	}
}

