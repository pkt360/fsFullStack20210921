package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service
//@Qualifier("otro")
public class OtroServicioImpl implements Servicio {

	@Override
	public String saluda() {
		return "Soy una tetera";
	}

}
