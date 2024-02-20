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

//    A DOCUMENTAÇÃO DOS METODOS A SEGUIR ESTÃO DISPONIVEIS ATRAVÉS DO SWAGGER NO LINK:
//    http://localhost:8080/swagger-ui/index.html

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid Veiculo veiculo){
        service.salvar(veiculo);
        return ResponseEntity.status(201).body(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar(){
        List<Veiculo> veiculos = service.listarTodos();

        if (veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(veiculos);
    }


    @GetMapping("/nome")
    public ResponseEntity<List<Veiculo>> listarPorNome(@RequestParam String nome){
        List<Veiculo> veiculos = service.pesquisarPorNome(nome);

        if (veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(veiculos);
    }


    @GetMapping("/fabricante")
    public ResponseEntity<List<Veiculo>> listarPorFabricante(@RequestParam String fabricante){
        List<Veiculo> veiculos = service.pesquisarPorFabricante(fabricante);

        if (veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/ano")
    public ResponseEntity<List<Veiculo>> listarPorAno(@RequestParam Integer ano){
        List<Veiculo> veiculos = service.pesquisarPorAno(ano);

        if (veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> detalharVeiculo(@PathVariable Integer id){
        return ResponseEntity.ok(service.pesquisarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> editar(@PathVariable Integer id, @RequestBody @Valid Veiculo veiculo){
        service.editarVeiculo(id, veiculo);
        return ResponseEntity.ok(veiculo);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> editarStatus(@PathVariable Integer id, @RequestParam String status){
        return ResponseEntity.ok(service.alterarStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable Integer id){
       return ResponseEntity.ok(service.deletarPorId(id));
    }

}
