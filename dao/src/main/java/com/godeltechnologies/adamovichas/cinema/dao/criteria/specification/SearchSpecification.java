package com.godeltechnologies.adamovichas.cinema.dao.criteria.specification;

import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.Deque;

public class SearchSpecification implements Specification {

    private SearchCriteria criteria;

    public SearchSpecification(final SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static Specification getSpecification(Deque<SearchCriteria> criterias) {
        Specification specification = Specification.where(new SearchSpecification(criterias.pollFirst()));
        while (!criterias.isEmpty()) {
            specification = specification.and(new SearchSpecification(criterias.pollFirst()));
        }
        return specification;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equals(">")) {
            return greaterThan(root, criteriaBuilder);
        } else if (criteria.getOperation().equals("<")) {
            return lesserThan(root, criteriaBuilder);
        } else if (criteria.getOperation().equals("=")) {
            return likeOrEquals(root, criteriaBuilder);
        }
        return null;
    }

    private Predicate greaterThan(Root root, CriteriaBuilder criteriaBuilder) {
        Class javaType = root.get(criteria.getKey()).getJavaType();
        if (javaType == LocalDate.class) {
            return criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get(criteria.getKey()), (LocalDate) criteria.getValue());
        } else {
            return criteriaBuilder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
        }
    }

    private Predicate lesserThan(Root root, CriteriaBuilder criteriaBuilder){
        Class javaType = root.get(criteria.getKey()).getJavaType();
        if (javaType == LocalDate.class) {
            return criteriaBuilder.lessThanOrEqualTo(root.<LocalDate>get(criteria.getKey()), (LocalDate) criteria.getValue());
        } else {
            return criteriaBuilder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
        }
    }

    private Predicate likeOrEquals(Root root, CriteriaBuilder criteriaBuilder){
        Class javaType = root.get(criteria.getKey()).getJavaType();
        if(javaType == String.class){
            return criteriaBuilder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
        } else {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }
}
