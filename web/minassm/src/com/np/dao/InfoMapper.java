package com.np.dao;

import java.util.List;

import com.np.domain.Info;

public interface InfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKeyWithBLOBs(Info record);

    int updateByPrimaryKey(Info record);

	List<Info> selectListInfo();
}