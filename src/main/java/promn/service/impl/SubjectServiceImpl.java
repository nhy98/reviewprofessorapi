package promn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import promn.dao.model.Subject;
import promn.dao.repository.SubjectRepository;
import promn.dao.specifications.SubjectSpecifications;
import promn.endpoint.dto.common.Result;
import promn.service.SubjectService;


@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;

	private Logger log = Logger.getLogger(SubjectServiceImpl.class);

	@Override
	public Page<Subject> getPage(Pageable pageable) {
		Page<Subject> subjectPage = null;
		try {
			subjectPage = subjectRepository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return subjectPage;
	}

	@Override
	public Page<Subject> getPage(Pageable pageable, Subject subject) {
		Page<Subject> subjectPage = null;
		try {
			subjectPage = subjectRepository.findAll(SubjectSpecifications.advanceSearch(subject), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return subjectPage;
	}

	@Override
	public Page<Subject> getPage(Pageable pageable, String filter) {
		Page<Subject> subjectPage = null;
		try {
			subjectPage = subjectRepository.findAll(SubjectSpecifications.filterSearch(filter), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return subjectPage;
	}

	@Override
	public List<Subject> getList() {
		List<Subject> subjectList = null;
		try {
			subjectList = subjectRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return subjectList;
	}

	@Override
	public Subject getOne(int id) {
		Subject subject = null;
		try {
			subject = subjectRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return subject;
	}

	@Override
	public Result create(Subject subject) {
		try {
			subjectRepository.save(subject);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result update(Subject subject) {
		try {
			subjectRepository.save(subject);
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
			subjectRepository.deleteById(id);
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
			List<Subject> lSubjects = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				lSubjects.add(subjectRepository.findById(ids[i]));
			}
			subjectRepository.deleteAll(lSubjects);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}
}


