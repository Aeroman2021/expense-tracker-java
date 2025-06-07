public interface IdentifiableCSVEntity<ID> extends Identifiable<ID> ,CsvSerializable{
    ID getId();

}
