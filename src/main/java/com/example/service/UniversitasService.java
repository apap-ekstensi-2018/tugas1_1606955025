package com.example.service;

import java.util.List;

import com.example.model.UniversitasModel;

public interface UniversitasService {
	
	UniversitasModel selectUniversitas (int id_univ);


    List<UniversitasModel> selectAllUniversitases ();

}
