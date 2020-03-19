package promn.dao.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import promn.dao.model.Review;


public class ReviewSpecifications {
	public static Specification<Review> filterSearch(String filter) {
		return new Specification<Review>() {

			@Override
			public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		};
	}

	public static Specification<Review> advanceSearch(Review review) {
		return new Specification<Review>() {

			@Override
			public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				// search in teacherRate column
				if (review.getTeacherRate() != 0) {
					obj = cb.equal(root.get("teacherRate"), review.getTeacherRate());
					predicateList.add(obj);
				}
				// search in subjectRate column
				if (review.getSubjectRate() != 0) {
					obj = cb.equal(root.get("subjectRate"), review.getSubjectRate());
					predicateList.add(obj);
				}
				// search in tag column
				if (review.getTag() != null) {
					obj = cb.like(cb.lower(root.get("tag")), "%" + review.getTag().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in content column
				if (review.getContent() != null) {
					obj = cb.like(cb.lower(root.get("content")), "%" + review.getContent().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in credit column
				if (review.getCredit() != 0) {
					obj = cb.equal(root.get("credit"), review.getCredit());
					predicateList.add(obj);
				}
				// search in attendance column
				if (review.getAttendance() != 0) {
					obj = cb.equal(root.get("attendance"), review.getAttendance());
					predicateList.add(obj);
				}
				// search in clazz column
				if (review.getClazz() != null && review.getClazz().getId() != 0) {
					obj = cb.equal(root.get("clazz").get("id"), review.getClazz().getId());
					predicateList.add(obj);
				}
				// search in user column
				if (review.getUser() != null && review.getUser().getId() != 0) {
					obj = cb.equal(root.get("user").get("id"), review.getUser().getId());
					predicateList.add(obj);
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}



