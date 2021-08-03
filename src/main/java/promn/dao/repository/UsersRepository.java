package promn.dao.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import promn.dao.model.User;

public interface UsersRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	
	Page<User> findByOrderByIdDesc(Pageable pageable);

	User findById(int id);
}
