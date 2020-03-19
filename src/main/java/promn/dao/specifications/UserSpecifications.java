package promn.dao.specifications;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import promn.dao.model.User;


public class UserSpecifications {
	public static Specification<User> filterSearch(String filter) {
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		};
	}

	public static Specification<User> advanceSearch(User user) {
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				// search in name column
				if (user.getName() != null) {
					obj = cb.like(cb.lower(root.get("name")), "%" + user.getName().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in role column
				if (user.getRole() != 0) {
					obj = cb.equal(root.get("role"), user.getRole());
					predicateList.add(obj);
				}
				// search in typeLogin column
				if (user.getTypeLogin() != 0) {
					obj = cb.equal(root.get("typeLogin"), user.getTypeLogin());
					predicateList.add(obj);
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}



