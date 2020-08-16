package com.challenge.inter.desafiojava.controllers;

import com.challenge.inter.desafiojava.entity.Usuario;
import com.challenge.inter.desafiojava.exception.UsuarioNaoEncontradoException;
import com.challenge.inter.desafiojava.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os usuarios")
    public ResponseEntity<List<?>> buscaTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity atualizar(@PathVariable("id") long id,
                                 @RequestBody Usuario usuario) {
        return usuarioService.findById(id)
                .map(record -> {
                    record.setNome(usuario.getNome());
                    record.setEmail(usuario.getEmail());
                    record.setPassword(usuario.getPassword());
                    Usuario usuarioAtualizado = usuarioService.save(record);
                    return ResponseEntity.ok().body(usuarioAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Deleta usuario unico")
    public ResponseEntity<?> deletaUsuario(@PathVariable Long usuarioId) {
        return usuarioService.findById(usuarioId).map(usuario -> {
            usuarioService.delete(usuario);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new UsuarioNaoEncontradoException("UsuarioId " + usuarioId + " not found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaPorId(
            @PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id).orElseThrow(RuntimeException::new));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um novo usuário")
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario){
        if(usuarioService.findByEmail(usuario.getEmail()).isPresent()){
            throw new IllegalArgumentException("Usuário já cadastrado.");
        }
        String senha = usuarioService.encryptRSA(usuario.getPassword());
        usuario.setPassword(senha);
        return ResponseEntity.ok(usuarioService.save(usuario));
    }
}
