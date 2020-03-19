package promn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import promn.dao.model.User;
import promn.endpoint.dto.common.Result;

/**
 * @author ThinhLH
 * @created 05/03/2019
 * 
 * @modified 05/03/2019
 * @modifier
 */

public interface UserService {

	/**
	 * 
	 * @param pageable
	 * @return a {@link Page} of user
	 */
	Page<User> getPage(Pageable pageable);

	/**
	 * 
	 * @param pageable
	 * @param user:  the search object
	 * @return a {@link Page} of user
	 */
	Page<User> getPage(Pageable pageable, User user);

	/**
	 * 
	 * @param pageable
	 * @param filter: the search keywords
	 * @return a {@link Page} of user
	 */
	Page<User> getPage(Pageable pageable, String filter);

	/**
	 * 
	 * @return a {@link List} of user
	 */
	List<User> getList();

	/**
	 * 
	 * @param id: the id of the user
	 * @return a {@link User}
	 */
	User getOne(int id);

	/**
	 * create a new user
	 * @param user: the new user
	 * @return a {@link Result}
	 */
	Result create(User user);

	/**
	 * update infomation of the user
	 * @param user: the new user
	 * @return a {@link Result}
	 */
	Result update(User user);

	/**
	 * 
	 * @param id: the id of the user
	 * @return  a {@link Result}
	 */
	Result delete(int id);

	/**
	 * delete a list user
	 * @param ids: the list id
	 * @return a {@link Result}
	 */
	Result delete(int[] ids);
}

