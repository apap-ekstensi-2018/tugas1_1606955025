package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.ProgramStudiModel;

@Mapper
public interface ProgramStudiMapper {

	@Select("select * from program_studi where id = #{id_prodi}")
    ProgramStudiModel selectProgramStudi (@Param("id_prodi") int id_prodi);

    @Select("select * from program_studi")
    List<ProgramStudiModel> selectAllProgramStudis ();
    
    @Select("select * from program_studi where id_fakultas = #{id_fakultas}")
    List<ProgramStudiModel> selectProgramStudiesByFakultas(@Param("id_fakultas") int id_fakultas);
}
