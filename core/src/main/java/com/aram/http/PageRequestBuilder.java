package com.aram.http;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * The class to build page request.
 */
public final class PageRequestBuilder{

    private PageRequestBuilder() {
        // Do nothing
    }

    /**
     * Constructs PageRequest
     *
     * @param pageSize the page size.
     * @param pageNumber the page numbers.
     * @param sortingCriteria the sorting criteria.
     * @return Returns page request.
     */
    public static PageRequest build(Integer pageSize, Integer pageNumber, String sortingCriteria) {

        Set<String> sortingFields = new LinkedHashSet<>(
                Arrays.asList(
                        StringUtils.split(StringUtils.defaultIfEmpty(sortingCriteria, ""), ",")
                )
        );
        List<Sort.Order> sortingOrders = sortingFields
                .stream()
                .map(PageRequestBuilder::getOrder)
                .collect(Collectors.toList());

        Sort sort = sortingOrders.isEmpty() ? Sort.unsorted() : Sort.by(sortingOrders);

        return PageRequest.of(ObjectUtils.defaultIfNull(pageNumber, 1) - 1, ObjectUtils.defaultIfNull(pageSize, 20), sort);
    }

    private static Sort.Order getOrder(String value) {

        if (StringUtils.startsWith(value, "-")) {
            return new Sort.Order(Sort.Direction.DESC, StringUtils.substringAfter(value, "-"));
        } else if (StringUtils.startsWith(value, "+")) {
            return new Sort.Order(Sort.Direction.ASC, StringUtils.substringAfter(value, "+"));
        } else {
            // Sometimes '+' from query param can be replaced as ' '
            return new Sort.Order(Sort.Direction.ASC, StringUtils.trim(value));
        }

    }

}
