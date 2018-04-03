package com.example.service;

import java.util.List;

import com.example.model.MahasiswaModel;

public interface MahasiswaService {
	
	MahasiswaModel selectMahasiswa (String npm);

    List<MahasiswaModel> selectAllMahasiswas ();
    
    MahasiswaModel selectOtherNPM (String npm);
    
    List<MahasiswaModel> selectMahasiswasByIdProdi(int id_prodi);
    
    int countAllMahasiswaByTahunMasukAndIdProdi (String tahun, int id_prodi);
    
    int countGraduatedMahasiswaByTahunMasukAndIdProdi (String tahun, int id_prodi);
    
    int countDropedOutByTahunMasukAndIdProdi (String tahun, int id_prodi);
    
    void addMahasiswa (MahasiswaModel mahasiswa);
    
    void updateMahasiswa (MahasiswaModel mahasiswa);

}
