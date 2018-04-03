package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService{
	
	@Autowired
    private MahasiswaMapper mahasiswaMapper;


    @Override
    public MahasiswaModel selectMahasiswa (String npm)
    {
        log.info ("select student with npm {}", npm);
        return mahasiswaMapper.selectMahasiswa (npm);
    }

    @Override
    public MahasiswaModel selectOtherNPM (String npm)
    {
        log.info ("select student with npm like {}", npm);
        return mahasiswaMapper.selectOtherNPM(npm + "%");
    }
    
    @Override
    public void addMahasiswa (MahasiswaModel mahasiswa) 
    {
    	log.info ("insert mahasiswa with npm {}", mahasiswa.getNpm());
        mahasiswaMapper.addMahasiswa(mahasiswa);
    }
    
    @Override
    public void updateMahasiswa (MahasiswaModel mahasiswa) 
    {
    	log.info ("update mahasiswa with id {}", mahasiswa.getId());
        mahasiswaMapper.updateMahasiswa(mahasiswa);
    }

    @Override
    public List<MahasiswaModel> selectAllMahasiswas ()
    {
        log.info ("select all students");
        return mahasiswaMapper.selectAllMahasiswas ();
    }
    
    @Override
    public int countAllMahasiswaByTahunMasukAndIdProdi (String tahun, int id_prodi){
    	log.info ("count mahasiswa by tahun masuk {} dan id prodi {}", tahun, id_prodi);
    	return mahasiswaMapper.countMahasiswaByTahunMasukAndIdProdiAndStatus(tahun, id_prodi, "%");
    }
    
    @Override
    public int countGraduatedMahasiswaByTahunMasukAndIdProdi (String tahun, int id_prodi){
    	log.info ("count mahasiswa lulus by tahun masuk {} dan id prodi {}", tahun, id_prodi);
    	return mahasiswaMapper.countMahasiswaByTahunMasukAndIdProdiAndStatus(tahun, id_prodi, "Lulus");
    }
    
    @Override
    public int countDropedOutByTahunMasukAndIdProdi (String tahun, int id_prodi){
    	log.info ("count mahasiswa drop out by tahun masuk {} dan id prodi {}", tahun, id_prodi);
    	return mahasiswaMapper.countMahasiswaByTahunMasukAndIdProdiAndStatus(tahun, id_prodi, "Drop Out");
    }
    
    @Override
    public List<MahasiswaModel> selectMahasiswasByIdProdi(int id_prodi){
    	log.info ("select mahasiswa berdasarkan id_prodi {}", id_prodi);
    	return mahasiswaMapper.selectMahasiswasByIdProdi(id_prodi);
    }
}
