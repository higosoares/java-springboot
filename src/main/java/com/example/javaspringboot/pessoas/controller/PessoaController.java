package com.example.javaspringboot.pessoas.controller;

import com.example.javaspringboot.pessoas.dto.PessoaDto;
import com.example.javaspringboot.pessoas.entity.Estado;
import com.example.javaspringboot.pessoas.entity.Pessoa;
import com.example.javaspringboot.pessoas.repository.EstadoRepository;
import com.example.javaspringboot.pessoas.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public List<Pessoa> Get() {
        return pessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        return pessoa.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/pessoa", method =  RequestMethod.POST)
    public Object Post(@Valid @RequestBody PessoaDto pessoaDto)
    {
        // convert DTO to entity
        Pessoa pessoaRequest = modelMapper.map(pessoaDto, Pessoa.class);

        Optional<Estado> estado = estadoRepository.findById(pessoaDto.getEstado_id());

        if (estado.isEmpty()) {
            return new ResponseEntity<>("Estado não encontrado!", HttpStatus.NOT_FOUND);
        }

        pessoaRequest.setEstado(estado.get());
        pessoaRequest.setEstado_id(pessoaRequest.getEstado_id());
        pessoaRequest.setPerfis(pessoaRequest.getPerfis());
        Pessoa pessoa = pessoaRepository.save(pessoaRequest);

        return new ResponseEntity<>(pessoa, HttpStatus.CREATED).getBody();
    }

    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Object> Put(@PathVariable(value = "id") long id, @Valid @RequestBody PessoaDto pessoaDto)
    {
        Optional<Pessoa> oldPessoa = pessoaRepository.findById(id);

        if(oldPessoa.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // convert DTO to entity
        Pessoa pessoaRequest = modelMapper.map(pessoaDto, Pessoa.class);

        Optional<Estado> estado = estadoRepository.findById(pessoaDto.getEstado_id());

        if (estado.isEmpty()) {
            return new ResponseEntity<>("Estado não encontrado!", HttpStatus.NOT_FOUND);
        }

        Pessoa pessoa = oldPessoa.get();
        pessoa.setNome(pessoaRequest.getNome());
        pessoa.setData(pessoaRequest.getData());
        pessoa.setEstado(estado.get());
        pessoa.setEstado_id(pessoaRequest.getEstado_id());
        pessoa.setPerfis(pessoaRequest.getPerfis());
        pessoaRepository.save(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(pessoa.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pessoaRepository.delete(pessoa.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
