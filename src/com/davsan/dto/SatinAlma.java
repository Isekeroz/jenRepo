package com.davsan.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity
public class SatinAlma 
{
	private List<Urun> selectedPro = new ArrayList<Urun>();
	private Kullanici user = null;
	private Odeme payment = null;
	
	@Id
	@Property("id")
	private ObjectId id=null;

	public SatinAlma(){
	}
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public SatinAlma(Kullanici loginUser) 
	{
		this.user = loginUser;
	}

	public void urunEkle(Urun u)
	{
		selectedPro.add(u);
	}

	public float getTutar() 
	{
		if(selectedPro.isEmpty())
			return 0;
		
		float total = 0;
		
		for(Urun u : selectedPro)
			total += u.getPrice();

		return total;
	}
	
	public List<Urun> getSelectedPro() {
		return selectedPro;
	}

	public void setSelectedPro(List<Urun> selectedPro) {
		this.selectedPro = selectedPro;
	}

	public Kullanici getUser() {
		return user;
	}

	public void setUser(Kullanici user) {
		this.user = user;
	}

	public Odeme getPayment() {
		return payment;
	}

	public void setPayment(Odeme payment) {
		this.payment = payment;
	}

	public void ode(String ot, Date tarih, float tutar) 
	{
		payment = new Odeme(ot, tarih, tutar);
	}
}
