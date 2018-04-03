package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.MahasiswaModel;

@Mapper
public interface MahasiswaMapper {

	//@Select("select * from mahasiswa join program_studi on  mahasiswa.id_prodi = program_studi.id where npm = #{npm}")
	@Select("select * from mahasiswa where npm = #{npm}")
    MahasiswaModel selectMahasiswa (@Param("npm") String npm);
	
	@Select("select * from mahasiswa where npm like #{npm} order by npm desc limit 0, 1")
	MahasiswaModel selectOtherNPM (@Param("npm") String npm);

    @Select("select * from mahasiswa")
    List<MahasiswaModel> selectAllMahasiswas ();
    
    @Select("SELECT * FROM mahasiswa where id_prodi = #{id_prodi}")
    List<MahasiswaModel> selectMahasiswasByIdProdi(@Param("id_prodi") int id_prodi);
    
    @Select("SELECT count(1) FROM mahasiswa where tahun_masuk = #{tahun} AND id_prodi = #{id_prodi} AND  status like #{status}")
    int countMahasiswaByTahunMasukAndIdProdiAndStatus(@Param("tahun") String tahun, @Param("id_prodi") int id_prodi, @Param ("status") String status);
    
    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, #{status}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    void addMahasiswa (MahasiswaModel mahasiswa);
    
    @Update("UPDATE mahasiswa set npm = #{npm}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, agama = #{agama}, golongan_darah = #{golongan_darah}, status = #{status}, tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{id_prodi} where id = #{id}")
    void updateMahasiswa (MahasiswaModel mahasiswa);
}
