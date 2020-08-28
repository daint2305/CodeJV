package com.learning.service;

import com.learning.model.Oto;
import com.learning.model.Person;

public interface CarService {
	
	public void addData(Oto o);
	public void delete(Oto o);
	public Oto getById(Long id);
	public void updateData(Oto o);
}
