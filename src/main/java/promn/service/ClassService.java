package promn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import promn.dao.model.Clazz;
import promn.endpoint.dto.common.Result;

/**
 * @author ThinhLH
 * @created 05/03/2019
 * 
 * @modified 05/03/2019
 * @modifier
 */

public interface ClassService {

	/**
	 * 
	 * @param pageable
	 * @return a {@link Page} of clazz
	 */
	Page<Clazz> getPage(Pageable pageable);

	/**
	 * 
	 * @param pageable
	 * @param clazz:  the search object
	 * @return a {@link Page} of clazz
	 */
	Page<Clazz> getPage(Pageable pageable, Clazz clazz);

	/**
	 * 
	 * @param pageable
	 * @param filter: the search keywords
	 * @return a {@link Page} of clazz
	 */
	Page<Clazz> getPage(Pageable pageable, String filter);

	/**
	 * 
	 * @return a {@link List} of clazz
	 */
	List<Clazz> getList();

	/**
	 * 
	 * @param id: the id of the clazz
	 * @return a {@link Clazz}
	 */
	Clazz getOne(int id);

	/**
	 * create a new clazz
	 * @param clazz: the new clazz
	 * @return a {@link Result}
	 */
	Result create(Clazz clazz);

	/**
	 * update infomation of the clazz
	 * @param clazz: the new clazz
	 * @return a {@link Result}
	 */
	Result update(Clazz clazz);

	/**
	 * 
	 * @param id: the id of the clazz
	 * @return  a {@link Result}
	 */
	Result delete(int id);

	/**
	 * delete a list clazz
	 * @param ids: the list id
	 * @return a {@link Result}
	 */
	Result delete(int[] ids);
}
