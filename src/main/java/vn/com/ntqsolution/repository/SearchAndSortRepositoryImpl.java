package vn.com.ntqsolution.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SearchAndSortRepositoryImpl implements SearchAndSortRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public <T> List<T> searchAndSort(String searchByFieldName, String searchValue, String sortByFieldName,
                                     String sortValue, Class<T> type, int pageNumber, int pageSize) {
        List<T> objects = new ArrayList<T>();
        Query query = new Query();
        if (searchByFieldName.equals("") || searchValue.equals("")) {
            query.with(Sort.by((sortValue.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC), sortByFieldName))
                    .with(PageRequest.of(pageNumber, pageSize));
            objects = (List<T>) mongoTemplate.find(query, type);
        } else {
            query.addCriteria(Criteria.where(searchByFieldName).regex(searchValue));
            query.with(Sort.by((sortValue.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC), sortByFieldName))
                    .with(PageRequest.of(pageNumber, pageSize));
            objects = (List<T>) mongoTemplate.find(query, type);
        }
        return objects;
    }
}
