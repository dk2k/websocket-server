package ru.outofrange.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ru.outofrange.model.Token;
import ru.outofrange.model.User;

public class TokenSpecification implements Specification<Token> {

	private static final String CONST_USER = "user";
	private static final String CONST_VALID = "valid";
	
	private User user;
	
	public TokenSpecification(User user){
		this.user = user;
	}
	
	@Override
	public Predicate toPredicate(Root<Token> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (user != null){
			predicates.add(cb.equal(root.<Boolean>get(CONST_VALID), true));
			predicates.add(cb.equal(root.<User>get(CONST_USER), user));
		} 
	    return andTogether(predicates, cb);
	}
	
	
	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
	    return cb.and(predicates.toArray(new Predicate[0]));
	}

}
