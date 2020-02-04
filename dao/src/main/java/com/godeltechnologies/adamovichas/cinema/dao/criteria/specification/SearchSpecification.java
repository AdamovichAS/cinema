package com.godeltechnologies.adamovichas.cinema.dao.criteria.specification;

import com.godeltechnologies.adamovichas.cinema.dao.entity.FilmEntity;
import com.godeltechnologies.adamovichas.cinema.model.search.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Deque;

public class SearchSpecification implements Specification {

    private SearchCriteria criteria;

    public SearchSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public static Specification getSpecification(Deque<SearchCriteria> criterias){
        Specification specification = Specification.where(new SearchSpecification(criterias.pollFirst()));
        while (!criterias.isEmpty()){
            specification = specification.and(new SearchSpecification(criterias.pollFirst()));
        }
        return specification;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }else if (criteria.getOperation().equalsIgnoreCase("=")){
            return criteriaBuilder.equal(
                    root.get(criteria.getKey()),criteria.getValue().toString());
        }
        return null;
    }

}
