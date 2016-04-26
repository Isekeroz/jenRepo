package com.davsan.dto;

import java.util.Date;

public class Odeme 
{
	private String odemeTipi=null;
	private Date tarih = null;
	private float ucret=0L;
	
	public Odeme(String odemeTipi, Date tarih, float ucret){
		this.odemeTipi=odemeTipi;
		this.tarih=tarih;
		this.ucret=ucret;
	}

	public String getOdemeTipi() {
		return odemeTipi;
	}

	public void setOdemeTipi(String odemeTipi) {
		this.odemeTipi = odemeTipi;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public float getUcret() {
		return ucret;
	}

	public void setUcret(float miktar) {
		this.ucret = miktar;
	}
	
}

