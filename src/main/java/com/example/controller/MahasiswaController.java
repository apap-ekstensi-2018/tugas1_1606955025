package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;
import com.example.service.FakultasService;
import com.example.service.MahasiswaService;
import com.example.service.ProgramStudiService;
import com.example.service.UniversitasService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MahasiswaController {
	
	@Autowired
    MahasiswaService mahasiswaDAO;
	
	@Autowired
	ProgramStudiService programStudiDAO;
	
	@Autowired
	FakultasService fakultasDAO;
	
	@Autowired
	UniversitasService universitasDAO;

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }
    
    @RequestMapping("/mahasiswa")
    public String view (Model model, @RequestParam(value ="npm", required = true) String npm){
    	MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
    	if(mahasiswa == null) {
    		model.addAttribute("npm", npm);
    		return "not-found";
    	}
    	model.addAttribute("student", mahasiswa);
    	
    	ProgramStudiModel programStudi = programStudiDAO.selectProgramStudi(mahasiswa.getId_prodi());
    	model.addAttribute("program_studi", programStudi);
    	
    	FakultasModel fakultas = fakultasDAO.selectFakultas(programStudi.getId_fakultas());
    	model.addAttribute("fakultas", fakultas);
    	
    	UniversitasModel universitas = universitasDAO.selectUniversitas(fakultas.getId_univ());
    	model.addAttribute("universitas", universitas);
    	
    	return "view";
    }
    
    @RequestMapping("/mahasiswa/tambah")
    public String add (@ModelAttribute("mahasiswa") MahasiswaModel mahasiswa, Model model)
    {
    	if(mahasiswa.getNama() == null){
    		return "form-add";
    	} else {
    		ProgramStudiModel programStudi = programStudiDAO.selectProgramStudi(mahasiswa.getId_prodi());
    		if(programStudi == null){
    			return "not-found-id-prodi";
    		}
        	mahasiswa.setNpm(getNPM(mahasiswa));
        	mahasiswa.setStatus("Aktif");
    		model.addAttribute("npm", mahasiswa.getNpm());
    		mahasiswaDAO.addMahasiswa(mahasiswa);
    		return "success-add";
    	}
        
    }
    
    public String getNPM(MahasiswaModel mahasiswa) {
    	String kodeTahunMasuk = mahasiswa.getTahun_masuk().substring(2, 4);
		
		ProgramStudiModel programStudi = programStudiDAO.selectProgramStudi(mahasiswa.getId_prodi());
		String kodeProgramStudi = programStudi.getKode_prodi();
		
		FakultasModel fakultas = fakultasDAO.selectFakultas(programStudi.getId_fakultas());
    	UniversitasModel universitas = universitasDAO.selectUniversitas(fakultas.getId_univ());
    	String kodeUniversitas = universitas.getKode_univ();
    	
    	String kodeJalurMasuk = "";
    	if (mahasiswa.getJalur_masuk().equals("Undangan Olimpiade")){
    		kodeJalurMasuk = "53";
    	} else if (mahasiswa.getJalur_masuk().equals("Undangan Reguler/SNMPTN")) {
    		kodeJalurMasuk = "54";
    	} else if (mahasiswa.getJalur_masuk().equals("Undangan Paralel/PPKB")) {
    		kodeJalurMasuk = "55";
    	} else if (mahasiswa.getJalur_masuk().equals("Ujian Tulis Bersama/SBMPTN")) {
    		kodeJalurMasuk = "57";
    	} else {
    		kodeJalurMasuk = "62";
    	} 
    	
    	MahasiswaModel otherMahasiswa = mahasiswaDAO.selectOtherNPM(kodeTahunMasuk + kodeUniversitas + kodeProgramStudi + kodeJalurMasuk);
    	String kodeUrutan = "";
    	if(otherMahasiswa.getNpm().equals("")) {
    		kodeUrutan = "001";
    	} else {
    		kodeUrutan = String.valueOf(Integer.parseInt(otherMahasiswa.getNpm().substring(9,12)) + 1001).substring(1,4) ;
    	}
    	
    	String npm = kodeTahunMasuk + kodeUniversitas + kodeProgramStudi + kodeJalurMasuk +  kodeUrutan;
    	
    	return npm;
    }
    
    @RequestMapping("/mahasiswa/ubah/{npm}")
    public String edit (@PathVariable(value = "npm") String npm, @ModelAttribute("mahasiswa") MahasiswaModel updatedMahasiswa, Model model)
    {
    	MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
    	
    	if(updatedMahasiswa.getNama() == null) {
    		
    		if(mahasiswa == null) {
    			model.addAttribute("npm", npm);
    			return "not-found";
    		}
        	model.addAttribute("mahasiswa", mahasiswa);
        	model.addAttribute("mahasiswaNpm", npm);
        	return "form-update";
    	} else {
    		
    		ProgramStudiModel programStudi = programStudiDAO.selectProgramStudi(updatedMahasiswa.getId_prodi());
    		if(programStudi == null){
    			return "not-found-id-prodi";
    		}
    		
    		if(mahasiswa.getTahun_masuk().equals(updatedMahasiswa.getTahun_masuk()) && mahasiswa.getJalur_masuk().equals(updatedMahasiswa.getJalur_masuk()) && mahasiswa.getId_prodi() == updatedMahasiswa.getId_prodi()){
    			updatedMahasiswa.setNpm(mahasiswa.getNpm());
    		} else {
    			updatedMahasiswa.setNpm(getNPM(updatedMahasiswa));
    		}
    		mahasiswaDAO.updateMahasiswa(updatedMahasiswa);
    		model.addAttribute("npm", mahasiswa	.getNpm());
    		return "success-update";
    	}
    	
    }
    
    @RequestMapping("/kelulusan")
    public String viewGraduation (Model model,
            @RequestParam(value = "thn", required = false) String tahun,
            @RequestParam(value = "prodi", required = false) String id_prodi)
    {
    	
    	if(tahun == null || id_prodi == null || tahun == "" || id_prodi == ""){
    		return "form-graduation";
    	} else {
    		model.addAttribute("tahun", tahun);
    		
    		int id_programStudi = Integer.parseInt(id_prodi);
    		ProgramStudiModel programStudi = programStudiDAO.selectProgramStudi(id_programStudi);
    		model.addAttribute("programStudi", programStudi);
    		
    		FakultasModel fakultas = fakultasDAO.selectFakultas(programStudi.getId_fakultas());
        	model.addAttribute("fakultas", fakultas);
        	
        	UniversitasModel universitas = universitasDAO.selectUniversitas(fakultas.getId_univ());
        	model.addAttribute("universitas", universitas);
        	
        	
        	int qtyAllMahasiswa = mahasiswaDAO.countAllMahasiswaByTahunMasukAndIdProdi(tahun, id_programStudi);
        	int qtyGraduatedMahasiswa =mahasiswaDAO.countGraduatedMahasiswaByTahunMasukAndIdProdi(tahun, id_programStudi);
        	//int qtyDorpedOutMahasiswa = mahasiswaDAO.countDropedOutByTahunMasukAndIdProdi(tahun, id_programStudi);
        	int presentaseKelulusan = (int)(((double)qtyGraduatedMahasiswa / (double)qtyAllMahasiswa) * 100);
        	model.addAttribute("presentaseKelulusan", presentaseKelulusan);
        	model.addAttribute("allMahasiswa", qtyAllMahasiswa);
        	model.addAttribute("graduatedMahasiswa", qtyGraduatedMahasiswa);
    		return "result-graduation";
    	}
    	
    }
    
    @RequestMapping("/mahasiswa/cari")
    public String findMahasiswa(Model model, 
    		@RequestParam(value = "univ", required = false) String univ,
    		@RequestParam(value = "fakultas", required = false) String fakultas,
    		@RequestParam(value = "prodi", required = false) String prodi)
    {
    	List<UniversitasModel> universitasList = universitasDAO.selectAllUniversitases();
    	model.addAttribute("universitas", universitasList);
    	
    	if(univ == null){
    		return "form-search-universitas";
    	} else {
    		int id_univ = Integer.parseInt(univ);
    		List<FakultasModel> fakultasList = fakultasDAO.selectFakultasesByUniversitas(id_univ);
    		model.addAttribute("selectedUniversitas", id_univ);
    		model.addAttribute("fakultas", fakultasList);
    		if(fakultas == null) {
        		return "form-search-fakultas";
    		} else {
    			int id_fakultas = Integer.parseInt(fakultas);
    			List<ProgramStudiModel> programStudiList = programStudiDAO.selectProgramStudiesByFakultas(id_fakultas);
    			model.addAttribute("selectedFakultas", id_fakultas);
        		model.addAttribute("programStudi", programStudiList);
        		
        		if(prodi != null){
        			int id_prodi = Integer.parseInt(prodi);
        			List<MahasiswaModel> mahasiswaList = mahasiswaDAO.selectMahasiswasByIdProdi(id_prodi);
        			model.addAttribute("mahasiswa", mahasiswaList);
        			
        			UniversitasModel universitasModel = universitasDAO.selectUniversitas(id_univ);
        			model.addAttribute("selectedUniversitasModel", universitasModel);
        			
        			FakultasModel fakultasModel = fakultasDAO.selectFakultas(id_fakultas);
        			model.addAttribute("selectedFakultasModel", fakultasModel);
        			
        			ProgramStudiModel programStudiModel = programStudiDAO.selectProgramStudi(id_prodi);
        			model.addAttribute("selectedProgramStudiModel", programStudiModel);
        			
        			return "result-search";
        		}
    			return "form-search-programstudi";
    		}
    		
    		
    	}
    	
    }
    
}
