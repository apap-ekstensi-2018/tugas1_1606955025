package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.UniversitasModel;

@Mapper
public interface UniversitasMapper {
	
	@Select("select * from universitas where id = #{id_univ}")
    UniversitasModel selectUniversitas (@Param("id_univ") int id_univ);

    @Select("select * from universitas")
    List<UniversitasModel> selectAllUniversitases ();

}
