package lk.intelleon.dao.custom.impl;

import lk.intelleon.dao.custom.RefDAO;
import lk.intelleon.entity.Ref;
import lk.intelleon.entity.User;
import lk.intelleon.utils.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/4/2022
 **/
public class RefDAOImpl implements RefDAO {
    @Override
    public boolean add(Ref ref) throws Exception {
        return CrudUtil.execute("INSERT INTO Ref VALUES(?,?,?,?,?)",
                ref.getRefId(),ref.getName(),
                ref.getIdNumber(),ref.getTel(),ref.getAddress());
    }

    @Override
    public boolean update(Ref ref) throws Exception {
        return CrudUtil.execute("UPDATE Ref SET name=?, idNumber=?, tel=?, address=? WHERE refid=?",
                ref.getName(),ref.getIdNumber(),ref.getTel(),ref.getAddress(),ref.getRefId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Ref Where refid=?",s);
    }

    @Override
    public Ref search(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Ref WHERE refid=?", s);

        if (rst.next ()){
            return new Ref(
                    rst.getString ( 1 ),
                    rst.getString ( 2 ),
                    rst.getString ( 3 ),
                    rst.getInt ( 4 ),
                    rst.getString ( 5 )
            );
        }
        return null;
    }

    @Override
    public List<Ref> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Ref");
        ArrayList<Ref> refs = new ArrayList<>();

        while (resultSet.next()){
            refs.add(new Ref(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5)
                    )
            );
        }
        return refs;
    }
}
