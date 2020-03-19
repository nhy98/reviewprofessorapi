package promn.dao.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import promn.dao.model.Report;


public class ReportSpecifications {
	public static Specification<Report> filterSearch(String filter) {
		return new Specification<Report>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;

				if (filter != null) {
					// search in content column
					obj = cb.like(cb.lower(root.get("content")), "%" + filter.toLowerCase() + "%");
					predicateList.add(obj);

					// search in reason column
					obj = cb.like(cb.lower(root.get("reason")), "%" + filter.toLowerCase() + "%");
					predicateList.add(obj);
				}

				return cb.or(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	public static Specification<Report> advanceSearch(Report report) {
		return new Specification<Report>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				Predicate obj = null;
				// search in semester column
				if (report.getContent() != null) {
					obj = cb.like(cb.lower(root.get("content")), "%" + report.getContent().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in room column
				if (report.getReason() != null) {
					obj = cb.like(cb.lower(root.get("reason")),
							"%" + report.getReason().toLowerCase() + "%");
					predicateList.add(obj);
				}
				// search in review column
				if (report.getReview() != null && report.getReview().getId() != 0) {
					obj = cb.equal(root.get("review").get("id"), report.getReview().getId());
					predicateList.add(obj);
				}
				
				// search in user column
				if (report.getUser() != null && report.getUser().getId() != 0) {
					obj = cb.equal(root.get("user").get("id"), report.getUser().getId());
					predicateList.add(obj);
				}

				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}
}



