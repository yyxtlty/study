package com.org.asean.javase.five.web.dao;

import com.org.asean.javase.five.web.domain.po.ApnUser;
import com.org.asean.javase.five.web.domain.po.ApnUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApnUserMapper {
    int countByExample(ApnUserExample example);

    int deleteByExample(ApnUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApnUser record);

    int insertSelective(ApnUser record);

    List<ApnUser> selectByExample(ApnUserExample example);

    ApnUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApnUser record, @Param("example") ApnUserExample example);

    int updateByExample(@Param("record") ApnUser record, @Param("example") ApnUserExample example);

    int updateByPrimaryKeySelective(ApnUser record);

    int updateByPrimaryKey(ApnUser record);
}