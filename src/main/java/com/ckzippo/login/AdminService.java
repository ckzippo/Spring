package com.ckzippo.login;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/21
 * TIME:下午4:27
 */
@Repository
public class AdminService extends SqlSessionDaoSupport implements AdminMapper {

    public AdminService() {

    }

    @Resource(name = "sqlSessionTemplate")
    public void setSuperSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Admin getAdminByAcc(@Param("acc") String acc) {
        return getSqlSession().selectOne("com.ckzippo.login.AdminMapper.getAdminByAcc", acc);
    }

    public List<Admin> getAllAdmin() {
        return getSqlSession().selectList("com.ckzippo.login.AdminMapper.getAllAdmin");
    }
}
