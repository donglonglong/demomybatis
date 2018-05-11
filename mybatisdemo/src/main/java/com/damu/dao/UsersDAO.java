package com.damu.dao;

import com.damu.entity.Users;
import com.damu.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by mouwe on 2017/5/23.
 */
public class UsersDAO {
    private SqlSession sqlSession;
    private List<Users> list;
    private Users user;

    private SqlSession getSession() {
        sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        return sqlSession;
    }

    /**
     * 查询全部用户
     * @return
     */
    public List<Users> findAll() {
        try {
            list = getSession().selectList("findUsers");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    /**
     * 查询单个用户根据编号
     * @return
     */
    public Users findById(Integer id) {
        try {
            user = getSession().selectOne("findUsers", new Users(id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    /**
     * 增加一个新用户数据到数据库的方法
     * @return
     */
    public Users addUser(Users user) {
        try {

            // 返回值：是insert执行过程中影响的行数
            getSession().insert("addUser", user);

            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user;
    }


    /**
     * 用于修改用户资料的方法
     * @return
     */
    public Users updateUsers(Users user) {
        try {

            // 返回值：是insert执行过程中影响的行数
            getSession().update("updateUser", user);

            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    /**
     * 用于修改用户资料的方法
     * @return
     */
    public void delUsers(Integer id) {
        try {

            // 返回值：是insert执行过程中影响的行数
            getSession().delete("delUser", id);

            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

}
