package promn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import promn.dao.model.User;
import promn.dao.repository.UsersRepository;
import promn.dao.specifications.UserSpecifications;
import promn.endpoint.dto.common.Result;
import promn.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersRepository userRepository;

	private Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public Page<User> getPage(Pageable pageable) {
		Page<User> userPage = null;
		try {
			userPage = userRepository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return userPage;
	}

	@Override
	public Page<User> getPage(Pageable pageable, User user) {
		Page<User> userPage = null;
		try {
			userPage = userRepository.findAll(UserSpecifications.advanceSearch(user), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return userPage;
	}

	@Override
	public Page<User> getPage(Pageable pageable, String filter) {
		Page<User> userPage = null;
		try {
			userPage = userRepository.findAll(UserSpecifications.filterSearch(filter), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return userPage;
	}

	@Override
	public List<User> getList() {
		List<User> userList = null;
		try {
			userList = userRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return userList;
	}

	@Override
	public User getOne(int id) {
		User user = null;
		try {
			user = userRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return user;
	}

	@Override
	public Result create(User user) {
		try {
			userRepository.save(user);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result update(User user) {
		try {
			userRepository.save(user);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result delete(int id) {
		try {
			userRepository.deleteById(id);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	@Transactional
	public Result delete(int[] ids) {
		try {
			List<User> lUsers = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				lUsers.add(userRepository.findById(ids[i]));
			}
			userRepository.deleteAll(lUsers);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}
}

