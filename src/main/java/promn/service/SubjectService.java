package promn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import promn.dao.model.Subject;
import promn.endpoint.dto.common.Result;



public interface SubjectService {

	/**
	 * 
	 * @param pageable
	 * @return a {@link Page} of subject
	 */
	Page<Subject> getPage(Pageable pageable);

	/**
	 * 
	 * @param pageable
	 * @param subject:  the search object
	 * @return a {@link Page} of subject
	 */
	Page<Subject> getPage(Pageable pageable, Subject subject);

	/**
	 * 
	 * @param pageable
	 * @param filter: the search keywords
	 * @return a {@link Page} of subject
	 */
	Page<Subject> getPage(Pageable pageable, String filter);

	/**
	 * 
	 * @return a {@link List} of subject
	 */
	List<Subject> getList();

	/**
	 * 
	 * @param id: the id of the subject
	 * @return a {@link Subject}
	 */
	Subject getOne(int id);

	/**
	 * create a new subject
	 * @param subject: the new subject
	 * @return a {@link Result}
	 */
	Result create(Subject subject);

	/**
	 * update infomation of the subject
	 * @param subject: the new subject
	 * @return a {@link Result}
	 */
	Result update(Subject subject);

	/**
	 * 
	 * @param id: the id of the subject
	 * @return  a {@link Result}
	 */
	Result delete(int id);

	/**
	 * delete a list subject
	 * @param ids: the list id
	 * @return a {@link Result}
	 */
	Result delete(int[] ids);
}

