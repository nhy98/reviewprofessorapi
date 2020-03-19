package promn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import promn.dao.model.Teacher;

/**
 * @author ThinhLH
 * @created 09/05/2019
 * 
 * @modified 
 * @modifier 
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {
	
	Page<Teacher> findByOrderByIdDesc(Pageable pageable);

	Teacher findById(int id);
}
