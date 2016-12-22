package com.ckzippo.login;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/21
 * TIME:下午3:27
 */
public interface AdminMapper {
    public Admin getAdminByAcc(@Param("acc") String acc);
    public List<Admin> getAllAdmin();
}
