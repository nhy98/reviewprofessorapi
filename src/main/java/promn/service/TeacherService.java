package promn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import promn.dao.model.Teacher;
import promn.endpoint.dto.common.Result;



public interface TeacherService {

	/**
	 * 
	 * @param pageable
	 * @return a {@link Page} of teacher
	 */
	Page<Teacher> getPage(Pageable pageable);

	/**
	 * 
	 * @param pageable
	 * @param teacher:  the search object
	 * @return a {@link Page} of teacher
	 */
	Page<Teacher> getPage(Pageable pageable, Teacher teacher);

	/**
	 * 
	 * @param pageable
	 * @param filter: the search keywords
	 * @return a {@link Page} of teacher
	 */
	Page<Teacher> getPage(Pageable pageable, String filter);

	/**
	 * 
	 * @return a {@link List} of teacher
	 */
	List<Teacher> getList();

	/**
	 * 
	 * @param id: the id of the teacher
	 * @return a {@link Teacher}
	 */
	Teacher getOne(int id);

	/**
	 * create a new teacher
	 * @param teacher: the new teacher
	 * @return a {@link Result}
	 */
	Result create(Teacher teacher);

	/**
	 * update infomation of the teacher
	 * @param teacher: the new teacher
	 * @return a {@link Result}
	 */
	Result update(Teacher teacher);

	/**
	 * 
	 * @param id: the id of the teacher
	 * @return  a {@link Result}
	 */
	Result delete(int id);

	/**
	 * delete a list teacher
	 * @param ids: the list id
	 * @return a {@link Result}
	 */
	Result delete(int[] ids);
}
