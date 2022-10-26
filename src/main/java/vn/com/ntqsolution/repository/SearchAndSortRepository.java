package vn.com.ntqsolution.repository;

import java.util.List;

public interface SearchAndSortRepository {
    public <T> List<T> searchAndSort(String searchByFieldName, String searchValue, String sortByFieldName,
                                     String sortValue, Class<T> tClass, int pageNumber, int pageSize);
}
