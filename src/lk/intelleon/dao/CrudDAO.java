package lk.intelleon.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface CrudDAO <T,ID> extends SuperDAO{
    boolean add ( T t ) throws Exception;

    boolean update ( T t ) throws Exception;

    boolean delete ( ID id ) throws Exception;

    T search ( ID id ) throws Exception;

    List< T > getAll ( ) throws Exception;
}
