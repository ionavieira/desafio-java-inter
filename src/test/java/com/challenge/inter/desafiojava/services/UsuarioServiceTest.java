package com.challenge.inter.desafiojava.services;

import com.challenge.inter.desafiojava.entity.Usuario;
import com.challenge.inter.desafiojava.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    public void usuario_whenFindAllUsers(){
        Usuario usuario1 = criarUsuario("Joao Arnaldo de Freitas", "jão@joao.com.br-joao", "jarnaldo@teste.com");

        Usuario usuario2 = criarUsuario("Daniel Luiz", "teste@123", "daniel@luiz.com");

        List<Usuario> result = usuarioRepository.findAll();

        assertThat(result, hasSize(2));
    }

    @Test
    @Transactional
    public void usuario_whenFindAllReturnsNothing() {
        List<Usuario> result = usuarioRepository.findAll();

        assertThat(result, hasSize(0));
    }

    @Test
    @Transactional
    public void usuario_whenFindByIdReturnsNothing() {
        Optional<Usuario> result = usuarioRepository.findById(1L);

        assertThat(result.isPresent(), is(Boolean.FALSE));
    }

    @Test
    @Transactional
    public void usuario_whenSavingShouldBeSuccessful() {

        Usuario usuario = criarUsuario("Daniel Luiz", "teste@123", "daniel@luiz.com");

        Usuario result = usuarioRepository.save(usuario);

        assertThat(result.getNome(), equalTo(usuario.getNome()));
    }

    @Test
    @Transactional
    public void usuario_whenDeleteShouldBeSuccessful() {
        Usuario usuario1 = criarUsuario("Joao Arnaldo de Freitas", "jão@joao.com.br-joao", "jarnaldo@teste.com");

        Usuario usuario2 = criarUsuario("Daniel Luiz", "teste@123", "daniel@luiz.com");

        List<Usuario> result = usuarioRepository.findAll();

        assertThat(result, hasSize(2));

        usuarioRepository.delete(usuario1);

        List<Usuario> result2 = usuarioRepository.findAll();

        assertThat(result2, hasSize(1));
    }


    @Test
    @Transactional
    private Usuario criarUsuario(String nome, String password, String email){
        Usuario usuario =new Usuario();

        usuario.setNome(nome);
        usuario.setPassword(password);
        usuario.setEmail(email);

        return usuarioRepository.save(usuario);
    }

}
