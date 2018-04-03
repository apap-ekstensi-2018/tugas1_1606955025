package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.FakultasModel;


@Mapper
public interface FakultasMapper {

	@Select("select * from fakultas where id = #{id_fakultas}")
    FakultasModel selectFakultas (@Param("id_fakultas") int id_fakultas);

    @Select("select * from fakultas")
    List<FakultasModel> selectAllFakultases ();
    
    @Select("select * from fakultas where id_univ = #{id_univ}")
    List<FakultasModel> selectFakultasesByUniversitas(@Param("id_univ") int id_univ);
}
