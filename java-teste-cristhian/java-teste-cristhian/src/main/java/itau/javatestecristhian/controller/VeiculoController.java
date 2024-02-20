package itau.javatestecristhian.controller;

import itau.javatestecristhian.entity.Veiculo;
import itau.javatestecristhian.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid Veiculo veiculo){
        service.salvar(veiculo);
        return ResponseEntity.status(201).body(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar(){
        List<Veiculo> veiculos = service.listarTodos();
        return ResponseEntity.ok(veiculos);
    }

}
