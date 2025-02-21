package c3.coworktech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.coworktech.repository.ReservasRepository;

@Service
public class ReservasService {
    @Autowired
    private ReservasRepository reservasRepository;

    
}