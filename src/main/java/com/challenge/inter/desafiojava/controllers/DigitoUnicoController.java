package com.challenge.inter.desafiojava.controllers;

import com.challenge.inter.desafiojava.entity.DigitoUnico;
import com.challenge.inter.desafiojava.entity.Usuario;
import com.challenge.inter.desafiojava.exception.UsuarioNaoEncontradoException;
import com.challenge.inter.desafiojava.services.DigitoUnicoService;
import com.challenge.inter.desafiojava.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/digitoUnico")
public class DigitoUnicoController {
    @Autowired
    private DigitoUnicoService digitoUnicoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os digitos")
    public List<DigitoUnico> buscaTodosDigitos(Pageable pageable) {
        return digitoUnicoService.findAll();
    }

    @PostMapping(value = { "/usuario", "/usuario/{usuarioId}"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria digito com ou sem usuario.")
    public DigitoUnico adicionarDigitoComUsuario(@PathVariable("usuarioId") Optional<Long> usuarioId, @RequestBody DigitoUnico digitoUnico) {
		DigitoUnico digitoUnicoSalvo = digitoUnicoService.save(digitoUnico);

		if(usuarioId.isPresent()){
		Optional<Usuario> usuario = usuarioService.findById(usuarioId.get());
            if(usuario.isPresent()) {
                List<DigitoUnico> digitoUnicos = usuario.get().getDigitoUnicos();
                digitoUnicos.add(digitoUnicoSalvo);
                usuario.get().setDigitoUnicos(digitoUnicos);
                usuarioService.save(usuario.get());
            }
            if(!usuario.isPresent()){
                throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
            }
		}
    return digitoUnicoSalvo;
	}


    @GetMapping("/{id}")
    public ResponseEntity<DigitoUnico> buscaPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(digitoUnicoService.findById(id).orElseThrow(RuntimeException::new));
    }
}
