package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;
import com.example.model.FakultasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceDatabase implements FakultasService {
	
	@Autowired
    private FakultasMapper fakultasMapper;

    @Override
    public FakultasModel selectFakultas (int id_fakultas)
    {
        log.info ("select fakultas with id {}", id_fakultas);
        return fakultasMapper.selectFakultas(id_fakultas);
    }


    @Override
    public List<FakultasModel> selectAllFakultases()
    {
        log.info ("select all fakultas");
        return fakultasMapper.selectAllFakultases();
    }
    
    @Override
    public List<FakultasModel> selectFakultasesByUniversitas(int id_univ)
    {
    	log.info ("select all fakultas with id univ {}", id_univ);
    	return fakultasMapper.selectFakultasesByUniversitas(id_univ);
    }

}
