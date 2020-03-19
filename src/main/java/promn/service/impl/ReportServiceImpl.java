package promn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import promn.dao.model.Report;
import promn.dao.repository.ReportRepository;
import promn.dao.specifications.ReportSpecifications;
import promn.endpoint.dto.common.Result;
import promn.service.ReportService;


@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportRepository reportRepository;

	private Logger log = Logger.getLogger(ReportServiceImpl.class);

	@Override
	public Page<Report> getPage(Pageable pageable) {
		Page<Report> reportPage = null;
		try {
			reportPage = reportRepository.findAll(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reportPage;
	}

	@Override
	public Page<Report> getPage(Pageable pageable, Report report) {
		Page<Report> reportPage = null;
		try {
			reportPage = reportRepository.findAll(ReportSpecifications.advanceSearch(report), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reportPage;
	}

	@Override
	public Page<Report> getPage(Pageable pageable, String filter) {
		Page<Report> reportPage = null;
		try {
			reportPage = reportRepository.findAll(ReportSpecifications.filterSearch(filter), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reportPage;
	}

	@Override
	public List<Report> getList() {
		List<Report> reportList = null;
		try {
			reportList = reportRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return reportList;
	}

	@Override
	public Report getOne(int id) {
		Report report = null;
		try {
			report = reportRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return report;
	}

	@Override
	public Result create(Report report) {
		try {
			reportRepository.save(report);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}

	@Override
	public Result update(Report report) {
		try {
			reportRepository.save(report);
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
			reportRepository.deleteById(id);
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
			List<Report> lReports = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				lReports.add(reportRepository.findById(ids[i]));
			}
			reportRepository.deleteAll(lReports);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return Result.BAD_REQUEST;
	}
}

