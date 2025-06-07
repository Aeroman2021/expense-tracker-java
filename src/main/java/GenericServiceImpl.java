import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GenericServiceImpl<T extends IdentifiableCSVEntity<ID>,ID> implements GenericService<T,ID>{

    protected final Map<ID,T> store = new HashMap<>();
    private final Function<String,T> fromCSVMapper;

    public GenericServiceImpl(Function<String, T> fromCSVMapper) {
        this.fromCSVMapper = fromCSVMapper;
    }

    @Override
    public T save(T t) {
        store.put(t.getId(), t);
        return t;
    }

    @Override
    public boolean update(T t) {
        if(store.get(t.getId()) != null){
            store.put(t.getId(),t);
            return true;
        }
        return false;
    }

    @Override
    public T findById(int id) {
        return store.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean deleteById(int id) {
        return store.remove(id) != null;
    }

    public void saveToFile(String fileName){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (T t : store.values()) {
                writer.write(t.toCSV());
                writer.newLine();
            }
        }catch (IOException e){
            System.err.println("❌ Error writing to file: " + e.getMessage());
        }
    }






}
