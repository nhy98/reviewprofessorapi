package promn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import promn.dao.model.Teacher;
import promn.dao.repository.TeacherRepository;
import promn.dao.specifications.TeacherSpecifications;
import promn.endpoint.dto.common.Result;
import promn.service.TeacherService;


@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;

	private Logger log = Logger.getLogger(TeacherServiceImpl.class);

	@Override
	public Page<Teacher> getPage(Pageable pageable) {
		Page<Teacher> teacherPage = null;
		try {
			teacherPage = teacherRepository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return teacherPage;
	}

	@Override
	public Page<Teacher> getPage(Pageable pageable, Teacher teacher) {
		Page<Teacher> teacherPage = null;
		try {
			teacherPage = teacherRepository.findAll(TeacherSpecifications.advanceSearch(teacher), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return teacherPage;
	}

	@Override
	public Page<Teacher> getPage(Pageable pageable, String filter) {
		Page<Teacher> teacherPage = null;
		try {
			teacherPage = teacherRepository.findAll(TeacherSpecifications.filterSearch(filter), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return teacherPage;
	}

	@Override
	public List<Teacher> getList() {
		List<Teacher> teacherList = null;
		try {
			teacherList = teacherRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return teacherList;
	}

	@Override
	public Teacher getOne(int id) {
		Teacher teacher = null;
		try {
			teacher = teacherRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return teacher;
	}

	@Override
	public Result create(Teacher teacher) {
		try {
			teacherRepository.save(teacher);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result update(Teacher teacher) {
		try {
			teacherRepository.save(teacher);
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
			teacherRepository.deleteById(id);
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
			List<Teacher> lTeachers = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				lTeachers.add(teacherRepository.findById(ids[i]));
			}
			teacherRepository.deleteAll(lTeachers);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}
}

