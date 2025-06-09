package core;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface GenericService<T,ID>{
    T save(T t);
    boolean update(T t);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean deleteById(ID id);
    List<T> search(Predicate<T> filter);
    int deleteAllBy(Predicate<T> filter);
}
