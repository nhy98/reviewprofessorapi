package promn.dao.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import promn.dao.model.Subject;

/**
 * @author ThinhLH
 * @created 09/05/2019
 * 
 * @modified 
 * @modifier 
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer>, JpaSpecificationExecutor<Subject> {
	
	Page<Subject> findByOrderByIdDesc(Pageable pageable);
	
	Subject findById(int id);

}
