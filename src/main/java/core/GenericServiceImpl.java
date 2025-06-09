package core;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericServiceImpl<T extends IdentifiableCSVEntity<ID>,ID> implements GenericService<T,ID> {

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
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean deleteById(ID id) {
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

    public void loadFromFile(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                T entity = fromCSVMapper.apply(line);
                store.put(entity.getId(),entity);
            }
        }catch (IOException e){
            System.err.println("❌ Error reading from file: " + e.getMessage());
        }
    }

    @Override
    public List<T> search(Predicate<T> filter) {
        return store.values().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    public int deleteAllBy(Predicate<T> filter) {
        List<ID> removeList = store.values().stream()
                .filter(filter)
                .map(IdentifiableCSVEntity::getId)
                .collect(Collectors.toList());

        removeList.forEach(store::remove);
        return removeList.size();
    }
}
