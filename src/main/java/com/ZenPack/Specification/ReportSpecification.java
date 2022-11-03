package com.ZenPack.Specification;

import com.ZenPack.Dto.SpecificationDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.model.Report;
import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static java.nio.file.Paths.get;

public class ReportSpecification implements Specification<Report> {
    SpecificationDto criteria;
    private Root<Report> root;
    private CriteriaQuery<?> query;
    private CriteriaBuilder builder;

    public ReportSpecification(){
    }

    public ReportSpecification(final SpecificationDto specificationDto){
        super();
        this.criteria=specificationDto;
    }


    @Override
    public Predicate toPredicate(final Root<Report> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
//        this.root = root;
//        this.query = query;
//        this.builder = builder;
        switch (criteria.getOperation()) {
            case "EQUALS":
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case "NOT_EQUAL":
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case "GREATER_THAN":
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case "LESS_THAN":
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case "LIKE":
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case "STARTS_WITH":
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case "ENDS_WITH":
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case "CONTAINS":
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case "N0T_CONTAINS":
                return builder.notLike(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case "BLANKS":
                return builder.notEqual(root.get(criteria.getKey()),criteria.getValue());
            case "NOT_BLANKS":
                return builder.equal(root.get(criteria.getKey()),criteria.getValue());
            default:
                return null;
        }
    }
}
