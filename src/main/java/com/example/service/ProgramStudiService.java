package com.example.service;

import java.util.List;

import com.example.model.ProgramStudiModel;

public interface ProgramStudiService {
	
	ProgramStudiModel selectProgramStudi (int id_prodi);


    List<ProgramStudiModel> selectAllProgramStudis ();

    
    List<ProgramStudiModel> selectProgramStudiesByFakultas (int id_fakultas);
}
