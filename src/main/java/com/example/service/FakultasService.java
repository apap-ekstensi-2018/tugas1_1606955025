package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;

public interface FakultasService {
	
	FakultasModel selectFakultas (int id_fakultas);


    List<FakultasModel> selectAllFakultases ();

    
    List<FakultasModel> selectFakultasesByUniversitas (int id_univ);

}
