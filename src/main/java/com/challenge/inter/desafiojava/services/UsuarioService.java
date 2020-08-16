package com.challenge.inter.desafiojava.services;

import com.challenge.inter.desafiojava.configurations.EncoderPassoword;
import com.challenge.inter.desafiojava.entity.DigitoUnico;
import com.challenge.inter.desafiojava.entity.Usuario;
import com.challenge.inter.desafiojava.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@AllArgsConstructor
public class UsuarioService implements UsuarioRepository {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(Long userId) {
        return usuarioRepository.findById(userId);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    public String encryptRSA(String senha){
        try{
            KeyPair privateKey = EncoderPassoword.generateKeyPair();
            return encrypt(senha, privateKey.getPublic());
        }catch (Exception e) {
            e.printStackTrace();
        } ;
        return null;
    }

    public String decrypt(String senha){
        try{
            KeyPair privateKey = EncoderPassoword.generateKeyPair();
            return decrypt(senha, privateKey.getPrivate());
        }catch (Exception e) {
            e.printStackTrace();
        } ;
        return null;
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findAll(Sort sort) {
        return usuarioRepository.findAll(sort);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public List<Usuario> findAllById(Iterable<Long> iterable) {
        return usuarioRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public void deleteAll(Iterable<? extends Usuario> iterable) {
        usuarioRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

    @Override
    public <S extends Usuario> List<S> saveAll(Iterable<S> iterable) {
        return usuarioRepository.saveAll(iterable);
    }

    @Override
    public void flush() {
        usuarioRepository.flush();
    }

    @Override
    public <S extends Usuario> S saveAndFlush(S s) {
        return usuarioRepository.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<Usuario> iterable) {
        usuarioRepository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        usuarioRepository.deleteAllInBatch();
    }

    @Override
    public Usuario getOne(Long id) {
        return usuarioRepository.getOne(id);
    }

    @Override
    public <S extends Usuario> Optional<S> findOne(Example<S> example) {
        return usuarioRepository.findOne(example);
    }

    @Override
    public <S extends Usuario> List<S> findAll(Example<S> example) {
        return (List<S>) usuarioRepository.findAll();
    }

    @Override
    public <S extends Usuario> List<S> findAll(Example<S> example, Sort sort) {
        return (List<S>) usuarioRepository.findAll(sort);
    }

    @Override
    public <S extends Usuario> Page<S> findAll(Example<S> example, Pageable pageable) {
        return (Page<S>) usuarioRepository.findAll(pageable);
    }

    @Override
    public <S extends Usuario> long count(Example<S> example) {
        return usuarioRepository.count();
    }

    @Override
    public <S extends Usuario> boolean exists(Example<S> example) {
        return usuarioRepository.exists(example);
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }


    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }
}
