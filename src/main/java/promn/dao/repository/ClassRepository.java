package promn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import promn.dao.model.Clazz;

/**
 * @author ThinhLH
 * @created 09/05/2019
 * 
 * @modified 
 * @modifier 
 */
public interface ClassRepository extends JpaRepository<Clazz, Integer>, JpaSpecificationExecutor<Clazz> {
	
	Page<Clazz> findByOrderByIdDesc(Pageable pageable);
	
	Clazz findById(int id);

}