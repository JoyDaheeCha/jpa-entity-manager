package persistence.entity.persistencecontext;

import java.util.Optional;

public interface PersistenceContext {

    <T> Optional<T> getEntity(Class<T> clazz, Long id);

    <T> T addEntity(T entity, Long id);

    <T> T updateEntity(T entity, Long id);

    void removeEntity(Object entity);

    /**
     * 스냅샷을 이용해 데이터를 조회한다.
     */
    <T> T getDatabaseSnapshot(T entity, Long id);

    Optional<EntityEntry> getEntityEntry(Class<?> clazz, Long id);
    void manageEntityEntry(Class<?> clazz, Long id);

    <T> void manageEntityEntry(T entity);

    <T> void saveEntryEntity(T entity);
}
