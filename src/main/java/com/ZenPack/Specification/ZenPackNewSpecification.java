package com.ZenPack.Specification;


import com.ZenPack.model.ZenPack;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class ZenPackNewSpecification {

    public Specification<ZenPack> getZenpacks(String type, String filter, String columnName) {
        return (root, query, criteriaBuilder) -> {
            ArrayList<String> names = new ArrayList<>();
            names.add("name");
            names.add("createdDate");
            names.add("updatedTime");
            names.add("createdBy");
            names.add("updatedBy");
            names.add("inActive");
            if (names.contains(columnName) && type.equals("contains")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(columnName)), "%" + filter
                        .toLowerCase() + "%");
            } else if (names.contains(columnName) && type.equals("not contains")) {
                return criteriaBuilder.notLike(criteriaBuilder.lower(root.<String>get(columnName)),
                        "%" + filter.toLowerCase() + "%");
            } else if (names.contains(columnName) && type.equals("equals")) {
                return criteriaBuilder.equal(root.<String>get(columnName), filter);
            } else if (names.contains(columnName) && type.equals("not equals")) {
                return criteriaBuilder.notEqual(root.<String>get(columnName), filter);
            }
            else if (names.contains(columnName) && type.equals("starts with")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(columnName)),
                        "*"+filter.toLowerCase());
            }
            else if (names.contains(columnName) && type.equals("ends with")) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(columnName)),
                        filter.toLowerCase()+"*" );
            }
            else if (names.contains(columnName) && type.equals("blanks")) {
                return criteriaBuilder.equal(root.<String>get(columnName),"" );
            }
            else if (names.contains(columnName) && type.equals("not blanks")) {
                return criteriaBuilder.notEqual(root.<String>get(columnName),"");
            }
            return criteriaBuilder.notEqual(root.<String>get(columnName), filter);
        };
    }


}
