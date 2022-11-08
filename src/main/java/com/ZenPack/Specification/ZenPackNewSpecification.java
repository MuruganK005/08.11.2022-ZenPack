package com.ZenPack.Specification;


import com.ZenPack.Dto.FilterDTO;
import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class ZenPackNewSpecification {

    public Specification<ZenPack> getZenpacks(FilterDTO zenpackDTO) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<String> names = new ArrayList<>();
            names.add("name");
            names.add("createdDate");
            names.add("updatedTime");
            names.add("createdBy");
            names.add("updatedBy");
            names.add("inActive");
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
                        "*"+zenpackDTO.getValue().toLowerCase());
            }
            else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("ends with")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(zenpackDTO.getKey())),
                         zenpackDTO.getValue().toLowerCase()+"*" );
            }
            else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("blanks")) {
                return criteriaBuilder.equal(root.<String>get(zenpackDTO.getKey()),"" );
            }
            else if (names.contains(zenpackDTO.getKey()) && zenpackDTO.getOperator().equals("not blanks")) {
                return criteriaBuilder.notEqual(root.<String>get(zenpackDTO.getKey()),"");
            }
            return criteriaBuilder.notEqual(root.<String>get(zenpackDTO.getKey()), zenpackDTO.getValue());
        };
    }
}
