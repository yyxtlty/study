package com.org.asean.javase.fourweb.dao;

import com.org.asean.javase.fourweb.domain.po.TSupplier;
import com.org.asean.javase.fourweb.domain.po.TSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSupplierMapper {
    int countByExample(TSupplierExample example);

    int deleteByExample(TSupplierExample example);

    int deleteByPrimaryKey(Long supplierId);

    int insert(TSupplier record);

    int insertSelective(TSupplier record);

    List<TSupplier> selectByExample(TSupplierExample example);

    TSupplier selectByPrimaryKey(Long supplierId);

    int updateByExampleSelective(@Param("record") TSupplier record, @Param("example") TSupplierExample example);

    int updateByExample(@Param("record") TSupplier record, @Param("example") TSupplierExample example);

    int updateByPrimaryKeySelective(TSupplier record);

    int updateByPrimaryKey(TSupplier record);
}