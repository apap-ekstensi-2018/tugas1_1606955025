package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UniversitasMapper;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UniversitasServiceDatabase implements UniversitasService{
	
	@Autowired
    private UniversitasMapper universitasMapper;

    @Override
    public UniversitasModel selectUniversitas (int id_univ)
    {
        log.info ("select program studi with id {}", id_univ);
        return universitasMapper.selectUniversitas(id_univ);
    }


    @Override
    public List<UniversitasModel> selectAllUniversitases()
    {
        log.info ("select all program studi");
        return universitasMapper.selectAllUniversitases();
    }
}
