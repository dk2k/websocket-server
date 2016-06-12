package ru.outofrange.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ru.outofrange.model.Session;

public class SessionSpecification implements Specification<Session> {

	private static final String CONST_SESSIONID = "sessionId";
	
	private String sessionId;
	
	public SessionSpecification(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public Predicate toPredicate(Root<Session> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (sessionId != null && !sessionId.equals("")){
			predicates.add(cb.equal(root.<String>get(CONST_SESSIONID), sessionId));
		} 
	    return andTogether(predicates, cb);
	}
	
	
	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
	    return cb.and(predicates.toArray(new Predicate[0]));
	}
	
}
