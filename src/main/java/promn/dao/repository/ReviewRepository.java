package promn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import promn.dao.model.Review;


public interface ReviewRepository extends JpaRepository<Review, Integer>, JpaSpecificationExecutor<Review> {
	
	Page<Review> findByOrderByIdDesc(Pageable pageable);

	Review findById(int id);
}
