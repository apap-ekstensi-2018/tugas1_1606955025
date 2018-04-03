package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProgramStudiMapper;
import com.example.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService {
	
	@Autowired
    private ProgramStudiMapper programStudiMapper;


    @Override
    public ProgramStudiModel selectProgramStudi (int id_prodi)
    {
        log.info ("select program studi with id {}", id_prodi);
        return programStudiMapper.selectProgramStudi (id_prodi);
    }


    @Override
    public List<ProgramStudiModel> selectAllProgramStudis ()
    {
        log.info ("select all program studi");
        return programStudiMapper.selectAllProgramStudis ();
    }
    
    @Override
    public List<ProgramStudiModel> selectProgramStudiesByFakultas(int id_fakultas)
    {
    	log.info ("select program studi by id fakultas, {}", id_fakultas);
    	return programStudiMapper.selectProgramStudiesByFakultas(id_fakultas);
    }
}
