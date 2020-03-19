package promn.dao.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import promn.dao.model.Clazz;


public class ClassSpecifications {
	@SuppressWarnings("serial")
	public static Specification<Clazz> filterSearch(String filter) {
		return new Specification<Clazz>() {

			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;

				if (filter != null) {
					// search in semester column
					obj = cb.like(cb.lower(root.get("semester")), "%" + filter.toLowerCase() + "%");
					predicateList.add(obj);

					// search in room column
					obj = cb.like(cb.lower(root.get("room")), "%" + filter.toLowerCase() + "%");
					predicateList.add(obj);
				}

				return cb.or(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	@SuppressWarnings("serial")
	public static Specification<Clazz> advanceSearch(Clazz clazz) {
		return new Specification<Clazz>() {

			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				// search in semester column
				if (clazz.getSemester() != null) {
					obj = cb.like(cb.lower(root.get("semester")), "%" + clazz.getSemester().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in room column
				if (clazz.getRoom() != null) {
					obj = cb.like(cb.lower(root.get("room")),
							"%" + clazz.getRoom().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in teacher column
				if (clazz.getTeacher() != null && clazz.getTeacher().getId() != 0) {
					obj = cb.equal(root.get("teacher").get("id"), clazz.getTeacher().getId());
					predicateList.add(obj);
				}
				
				// search in subject column
				if (clazz.getSubject() != null && clazz.getSubject().getId() != 0) {
					obj = cb.equal(root.get("subject").get("id"), clazz.getSubject().getId());
					predicateList.add(obj);
				}

				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}



