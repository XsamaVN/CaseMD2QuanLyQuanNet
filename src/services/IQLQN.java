package services;

import java.io.IOException;

public interface IQLQN<T> {
    void addNew(T t) throws IOException;
    void edit(int t) throws IOException;
    void delete(int t) throws IOException;
    void showAll() throws IOException;
    void findById(int t) throws IOException;

}
