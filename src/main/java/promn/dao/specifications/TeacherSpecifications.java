package promn.dao.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import promn.dao.model.Teacher;


public class TeacherSpecifications {
	public static Specification<Teacher> filterSearch(String filter) {
		return new Specification<Teacher>() {

			@Override
			public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		};
	}

	public static Specification<Teacher> advanceSearch(Teacher teacher) {
		return new Specification<Teacher>() {

			@Override
			public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				// search in name column
				if (teacher.getName() != null) {
					obj = cb.like(cb.lower(root.get("name")), "%" + teacher.getName().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in age column
				if (teacher.getAge() != 0) {
					obj = cb.equal(root.get("age"), teacher.getAge());
					predicateList.add(obj);
				}
				// search in address column
				if (teacher.getAddress() != null) {
					obj = cb.like(cb.lower(root.get("address")), "%" + teacher.getAddress().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in phoneNumber column
				if (teacher.getPhoneNumber() != null) {
					obj = cb.like(cb.lower(root.get("phoneNumber")), "%" + teacher.getPhoneNumber().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in email column
				if (teacher.getEmail() != null) {
					obj = cb.like(cb.lower(root.get("email")), "%" + teacher.getEmail().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in school column
				if (teacher.getSchool() != null) {
					obj = cb.like(cb.lower(root.get("school")), "%" + teacher.getSchool().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in achievement column
				if (teacher.getAchievement() != null) {
					obj = cb.like(cb.lower(root.get("achivement")), "%" + teacher.getAchievement().toLowerCase() + "%");
					predicateList.add(obj);
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}



