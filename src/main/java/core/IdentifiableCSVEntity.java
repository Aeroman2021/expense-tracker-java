package core;

public interface IdentifiableCSVEntity<ID> {
    ID getId();
    String toCSV();

}
