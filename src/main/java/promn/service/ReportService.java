package promn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import promn.dao.model.Report;
import promn.endpoint.dto.common.Result;


public interface ReportService {

	/**
	 * 
	 * @param pageable
	 * @return a {@link Page} of report
	 */
	Page<Report> getPage(Pageable pageable);

	/**
	 * 
	 * @param pageable
	 * @param report:  the search object
	 * @return a {@link Page} of report
	 */
	Page<Report> getPage(Pageable pageable, Report report);

	/**
	 * 
	 * @param pageable
	 * @param filter: the search keywords
	 * @return a {@link Page} of report
	 */
	Page<Report> getPage(Pageable pageable, String filter);

	/**
	 * 
	 * @return a {@link List} of report
	 */
	List<Report> getList();

	/**
	 * 
	 * @param id: the id of the report
	 * @return a {@link Report}
	 */
	Report getOne(int id);

	/**
	 * create a new report
	 * @param report: the new report
	 * @return a {@link Result}
	 */
	Result create(Report report);

	/**
	 * update infomation of the report
	 * @param report: the new report
	 * @return a {@link Result}
	 */
	Result update(Report report);

	/**
	 * 
	 * @param id: the id of the report
	 * @return  a {@link Result}
	 */
	Result delete(int id);

	/**
	 * delete a list report
	 * @param ids: the list id
	 * @return a {@link Result}
	 */
	Result delete(int[] ids);
}
