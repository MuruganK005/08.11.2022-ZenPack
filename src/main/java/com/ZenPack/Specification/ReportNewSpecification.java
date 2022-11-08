package com.ZenPack.Specification;

import com.ZenPack.Dto.FilterDTO;
import com.ZenPack.Dto.ZenPackFilterDTO;
import com.ZenPack.model.Report;
import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ReportNewSpecification {


    public Specification<Report> getReports(FilterDTO zenpackDTO) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<String> names = new ArrayList<>();
            names.add("feature");
            names.add("category");
            names.add("osType");
            names.add("discoveryType");
            names.add("analyticsBy");
            if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("contains")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(zenpackDTO.getKey())), "%" + zenpackDTO.getValue()
                        .toLowerCase() + "%");
            } else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("not contains")) {
                return criteriaBuilder.notLike(criteriaBuilder.lower(root.<String>get(zenpackDTO.getKey())),
                        "%" + zenpackDTO.getValue().toLowerCase() + "%");
            } else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("equals")) {
                return criteriaBuilder.equal(root.<String>get(zenpackDTO.getKey()), zenpackDTO.getValue());
            } else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("not equals")) {
                return criteriaBuilder.notEqual(root.<String>get(zenpackDTO.getKey()), zenpackDTO.getValue());
            }
            else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("starts with")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(zenpackDTO.getKey())),
                        "%"+zenpackDTO.getValue().toLowerCase());
            }
            else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("ends with")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(zenpackDTO.getKey())),
                        zenpackDTO.getValue().toLowerCase()+ "%" );
            }
            else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("blanks")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(zenpackDTO.getKey())), "%" + zenpackDTO.getValue()
                        .toLowerCase() + "%");
            }
            else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("not blanks")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(zenpackDTO.getKey())), "%" + zenpackDTO.getValue()
                        .toLowerCase() + "%");
            }
            return criteriaBuilder.notEqual(root.<String>get(zenpackDTO.getKey()), zenpackDTO.getValue());
        };
    }

    /*public Specification<ZenPack> getZenpackByDateRange(ZenPackFilterDTO zenpackFilterDTO) {
        String str = zenpackFilterDTO.getStartDate();
        String str1 = str;
        return (root, query, criteriaBuilder) -> {
            ArrayList<String> names = new ArrayList<>();
            if (zenpackFilterDTO.getStartDate() != null && zenpackFilterDTO.getEndDate() != null) {
                return criteriaBuilder.between(root.<String>get("updatedTime"), zenpackFilterDTO.getStartDate(), zenpackFilterDTO.getEndDate());
            }
            return null;
        };
    }*/
}
