import java.util.List;

public interface GenericService<T,ID>{
    T save(T t);
    boolean update(T t);
    T findById(int id);
    List<T> findAll();
    boolean deleteById(int id);
}
