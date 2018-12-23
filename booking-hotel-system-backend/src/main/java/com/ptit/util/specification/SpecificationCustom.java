package com.ptit.util.specification;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ptit.util.MethodUtil;


public class SpecificationCustom<T> implements Specification<T> {

  // declare Custom Criteria
  private CriteriaCustom criteria;

  public SpecificationCustom(CriteriaCustom criteria) {
    this.criteria = criteria;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    switch (criteria.getOperation()) {
    case EQUAL:
      return builder.equal(getKey(root), criteria.getValue());
    case LIKE:
      return builder.like(getKey(root),
          "%" + formatValueKeywordSearch(criteria.getValue().toString()) + "%");
    case GREATER_THAN_OR_EQUAL_DATE:
      return builder.greaterThanOrEqualTo(getKey(root),
          getFirstTimeOfDay((Date) criteria.getValue()));
    case LESS_THAN_OR_EQUAL_DATE:
      return builder.lessThanOrEqualTo(getKey(root), getLastTimeOfDay((Date) criteria.getValue()));
    case GREATER_THAN_OR_EQUAL_INT:
      return builder.greaterThanOrEqualTo(getKey(root), (Integer) criteria.getValue());
    case LESS_THAN_OR_EQUAL_INT:
      return builder.lessThanOrEqualTo(getKey(root), (Integer) criteria.getValue());
    case IN:
      return getKey(root).in((List<Object>) criteria.getValue());
    case FULL_TEXT_SEARCH:
      break;

    default:
      break;
    }
    return null;
  }


  private String formatValueKeywordSearch(String keyword) {
    keyword = keyword.replace("%", "\\%");
    keyword = keyword.replace("_", "\\_");
    keyword = MethodUtil.removeAccent(keyword);
    return keyword;
  }


  @SuppressWarnings("rawtypes")
  private Path getKey(Root root) {
    Path keys = getRootJoin(root, criteria.getKey().get(0));
    if (criteria.getKey().size() > 1) {
      for (int i = 1; i < criteria.getKey().size(); i++) {
        keys = keys.get(criteria.getKey().get(i));
      }
    }
    return keys;
  }

 
  @SuppressWarnings("rawtypes")
  private Path getRootJoin(Root root, String key) {
    // if key is List
    if (key.endsWith("Collection")) {
      return root.join(key);
    }
    // else key is attribute
    return root.get(criteria.getKey().get(0));
  }

  @SuppressWarnings("deprecation")
  private Date getFirstTimeOfDay(Date date) {
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    return date;
  }

  @SuppressWarnings("deprecation")
  private Date getLastTimeOfDay(Date date) {
    date.setHours(23);
    date.setMinutes(59);
    date.setSeconds(59);
    return date;
  }
}
