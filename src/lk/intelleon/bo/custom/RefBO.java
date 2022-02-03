package lk.intelleon.bo.custom;

import lk.intelleon.bo.SuperBO;
import lk.intelleon.dto.RefDTO;
import lk.intelleon.dto.UserDTO;

import java.util.ArrayList;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/4/2022
 **/
public interface RefBO extends SuperBO {
    boolean addRef( RefDTO refDTO) throws Exception ;

    boolean deleteRef(String id) throws Exception ;

    RefDTO searchRef(String id) throws Exception ;

    boolean updateRef(RefDTO refDTO) throws Exception ;

    ArrayList<RefDTO> getAllRefs() throws Exception ;
}
