package promn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import promn.dao.model.Clazz;
import promn.dao.repository.ClassRepository;
import promn.dao.specifications.ClassSpecifications;
import promn.endpoint.dto.common.Result;
import promn.service.ClassService;


@Service
public class ClassServiceImpl implements ClassService{

	@Autowired
	private ClassRepository clazzRepository;

	private Logger log = Logger.getLogger(ClassServiceImpl.class);

	@Override
	public Page<Clazz> getPage(Pageable pageable) {
		Page<Clazz> clazzPage = null;
		try {
			clazzPage = clazzRepository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return clazzPage;
	}

	@Override
	public Page<Clazz> getPage(Pageable pageable, Clazz clazz) {
		Page<Clazz> clazzPage = null;
		try {
			clazzPage = clazzRepository.findAll(ClassSpecifications.advanceSearch(clazz), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return clazzPage;
	}

	@Override
	public Page<Clazz> getPage(Pageable pageable, String filter) {
		Page<Clazz> clazzPage = null;
		try {
			clazzPage = clazzRepository.findAll(ClassSpecifications.filterSearch(filter), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return clazzPage;
	}

	@Override
	public List<Clazz> getList() {
		List<Clazz> clazzList = null;
		try {
			clazzList = clazzRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return clazzList;
	}

	@Override
	public Clazz getOne(int id) {
		Clazz clazz = null;
		try {
			clazz = clazzRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return clazz;
	}

	@Override
	public Result create(Clazz clazz) {
		try {
			clazzRepository.save(clazz);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result update(Clazz clazz) {
		try {
			clazzRepository.save(clazz);
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
			clazzRepository.deleteById(id);
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
			List<Clazz> lClazzs = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				lClazzs.add(clazzRepository.findById(ids[i]));
			}
			clazzRepository.deleteAll(lClazzs);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}
}

