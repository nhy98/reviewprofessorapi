package promn.dao.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import promn.dao.model.Subject;


public class SubjectSpecifications {
	public static Specification<Subject> filterSearch(String filter) {
		return new Specification<Subject>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;

				if (filter != null) {
					// search in name column
					obj = cb.like(cb.lower(root.get("name")), "%" + filter.toLowerCase() + "%");
					predicateList.add(obj);
				}
				return cb.or(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	public static Specification<Subject> advanceSearch(Subject subject) {
		return new Specification<Subject>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				// search in name column
				if (subject.getName() != null) {
					obj = cb.like(cb.lower(root.get("name")), "%" + subject.getName().toLowerCase() + "%");
					predicateList.add(obj);
				}

				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}



