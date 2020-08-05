package es.eoi.mundobancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository repository;
	
}
