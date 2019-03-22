package daos;

import java.util.List;

public abstract class DAO<T extends DTO> {
    abstract public T findById(int id);
    abstract public List<T> findAll();
    abstract public T update(T dto);
    abstract public T create(T dto);
    abstract public void delete(int id);
}
