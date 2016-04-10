
package com.library.db;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public interface IDBAccess<T, K> {
    public List<T> queryAll();

    public T query(K key);
    
    public QueryBuilder<T> queryBuilder();

    public void insert(T entity);
    
    public void insert(Iterable<T> entities);
    
    public void insertOrReplace(T entity);
    
    public void insertOrReplaceInTx(Iterable<T> entities);

    public List<T> queryRaw(String where, String[] selectionArg);
    
    public void update(T entity);
    
    public void update(Iterable<T> entities);
    
    public void delete(T entity);

    public void deleteByKey(K key);

    public void deleteByKeyInTx(K... keys);
    
    public void deleteInTx(Iterable<T> entities);
    
    public void deleteByKeyInTx(Iterable<K> keys);

    public Long count();
    
    
}
