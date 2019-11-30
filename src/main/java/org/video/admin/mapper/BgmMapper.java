package org.video.admin.mapper;


import org.apache.ibatis.annotations.Param;
import org.video.admin.pojo.Bgm;
import org.video.admin.pojo.BgmExample;


import java.util.List;

public interface BgmMapper {
    int countByExample(BgmExample example);

    int deleteByExample(BgmExample example);

    int deleteByPrimaryKey(String id);

    public int insert(Bgm record);

    int insertSelective(Bgm record);

    List<Bgm> selectByExample(BgmExample example);

    Bgm selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Bgm record, @Param("example") BgmExample example);

    int updateByExample(@Param("record") Bgm record, @Param("example") BgmExample example);

    int updateByPrimaryKeySelective(Bgm record);

    int updateByPrimaryKey(Bgm record);
}