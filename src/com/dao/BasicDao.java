package com.dao;
import com.utils.JdbcUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

public class BasicDao<T> {

    private QueryRunner queryRunner = new QueryRunner();

    //DML
    public int sqlDML(String sql, Object...parameters) throws Exception {

        Connection connection = JdbcUtilsByDruid.getConnection();
        int affectedRows = queryRunner.update(connection, sql, parameters);

        JdbcUtilsByDruid.close(null, null, connection);

        return affectedRows;
    }


    /**
     *
     * @param sql sql select
     * @param tClass eg: Actor.class
     * @param parameters
     * @return ArrayList according to passed Class object
     * @throws Exception
     */
    public List<T> queryMultiple(String sql, Class<T> tClass, Object...parameters) throws Exception {

        Connection connection = JdbcUtilsByDruid.getConnection();
        List<T> list = queryRunner.query(connection, sql, new BeanListHandler<T>(tClass), parameters);

        JdbcUtilsByDruid.close(null, null, connection);

        return list;
    }

    /**
     *
     * @param sql sql select
     * @param tClass eg: Actor.class
     * @param parameters
     * @return single object according to passed Class object
     * @throws Exception
     */
    public T querySingle(String sql, Class<T> tClass, Object...parameters) throws Exception {

        Connection connection = JdbcUtilsByDruid.getConnection();
        T query = queryRunner.query(connection, sql, new BeanHandler<T>(tClass), parameters);

        JdbcUtilsByDruid.close(null, null, connection);

        return query;
    }


    public Object queryScalar(String sql, Object...parameters) throws Exception {

        Connection connection = JdbcUtilsByDruid.getConnection();
        Object ob = queryRunner.query(connection, sql, new ScalarHandler(), parameters);

        JdbcUtilsByDruid.close(null, null, connection);

        return ob;
    }
}
