package promn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import promn.dao.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>, JpaSpecificationExecutor<Report> {
	
	Page<Report> findByOrderByIdDesc(Pageable pageable);
	
	Report findById(int id);

}
