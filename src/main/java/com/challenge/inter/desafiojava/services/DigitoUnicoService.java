package com.challenge.inter.desafiojava.services;

import com.challenge.inter.desafiojava.entity.DigitoUnico;
import com.challenge.inter.desafiojava.repositories.DigitoUnicoRepository;
import com.challenge.inter.desafiojava.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DigitoUnicoService implements DigitoUnicoRepository {
    @Autowired
    private DigitoUnicoRepository digitoUnicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DigitoUnico save(DigitoUnico digitoUnico){
        digitoUnico.setId(generatedUniqueDigit(digitoUnico.getN(), digitoUnico.getK()));

        return digitoUnicoRepository.save(digitoUnico);
    }

    public Integer generatedUniqueDigit(String n, Integer k) {
        String nAux = n;

        for(int i = 0; i<k-1; i++){
            n += nAux;
        }

        while (n.length() > 1) {
            int sum = 0;
            for (char c : n.toCharArray()) {
                int digit = c - '0';
                sum += digit;
            }
            n = "" + sum;
        }
        return Integer.parseInt(n);
    }

    @Override
    public Optional<DigitoUnico> findByNAndK(String n, Integer k) {
        return digitoUnicoRepository.findByNAndK(n, k);
    }

    @Override
    public List<DigitoUnico> findAll() {
        return digitoUnicoRepository.findAll();
    }

    @Override
    public List<DigitoUnico> findAll(Sort sort) {
        return digitoUnicoRepository.findAll(sort);
    }

    @Override
    public Page<DigitoUnico> findAll(Pageable pageable) {
        return digitoUnicoRepository.findAll(pageable);
    }

    @Override
    public List<DigitoUnico> findAllById(Iterable<Long> iterable) {
        return digitoUnicoRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return digitoUnicoRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        digitoUnicoRepository.deleteById(id);
    }

    @Override
    public void delete(DigitoUnico digitoUnico) {
        digitoUnicoRepository.delete(digitoUnico);

    }

    @Override
    public void deleteAll(Iterable<? extends DigitoUnico> iterable) {
        digitoUnicoRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        digitoUnicoRepository.deleteAll();
    }

    @Override
    public <S extends DigitoUnico> List<S> saveAll(Iterable<S> iterable) {
        return saveAll(iterable);
    }

    @Override
    public Optional<DigitoUnico> findById(Long id) {
        return digitoUnicoRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return digitoUnicoRepository.existsById(id);
    }

    @Override
    public void flush() {
        digitoUnicoRepository.flush();
    }

    @Override
    public <S extends DigitoUnico> S saveAndFlush(S s) {
        return digitoUnicoRepository.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<DigitoUnico> iterable) {
        deleteAllInBatch();
    }

    @Override
    public void deleteAllInBatch() {
        deleteAllInBatch();
    }

    @Override
    public DigitoUnico getOne(Long id) {
        return digitoUnicoRepository.getOne(id);
    }

    @Override
    public <S extends DigitoUnico> Optional<S> findOne(Example<S> example) {
        return digitoUnicoRepository.findOne(example);
    }

    @Override
    public <S extends DigitoUnico> List<S> findAll(Example<S> example) {
        return digitoUnicoRepository.findAll(example);
    }

    @Override
    public <S extends DigitoUnico> List<S> findAll(Example<S> example, Sort sort) {
        return digitoUnicoRepository.findAll(example,sort);
    }

    @Override
    public <S extends DigitoUnico> Page<S> findAll(Example<S> example, Pageable pageable) {
        return digitoUnicoRepository.findAll(example, pageable);
    }

    @Override
    public <S extends DigitoUnico> long count(Example<S> example) {
        return digitoUnicoRepository.count();
    }

    @Override
    public <S extends DigitoUnico> boolean exists(Example<S> example) {
        return digitoUnicoRepository.exists(example);
    }
}
