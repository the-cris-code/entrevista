package itau.javatestecristhian.service;

import itau.javatestecristhian.entity.Veiculo;
import itau.javatestecristhian.exception.NoContentException;
import itau.javatestecristhian.exception.StatusInvalidoException;
import itau.javatestecristhian.exception.VeiculoExisteException;
import itau.javatestecristhian.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public Boolean verificaStatusCorreto(String status){
        if (status.equalsIgnoreCase("ACTIVATED") ||
                status.equalsIgnoreCase("DEACTIVATED") ||
                status.equalsIgnoreCase("RESERVED") ||
                status.equalsIgnoreCase("RENTED")){
            return true;
        }
        return false;
    }

    public Veiculo salvar(Veiculo novoVeiculo){
        Optional<Veiculo> veiculoOpt = repository.findByPlaca(novoVeiculo.getPlaca());
        Optional<Veiculo> veiculoOpt2 = repository.findByChassi(novoVeiculo.getChassi());

        if (veiculoOpt.isPresent() && veiculoOpt2.isPresent()){
            throw new VeiculoExisteException("Conflito: Veiculo com placa ou chassi já cadastrado");
        } else if (!verificaStatusCorreto(novoVeiculo.getStatus())){
            throw new StatusInvalidoException("O status deve ser 'ACTIVATED'," +
                    " 'DEACTIVATED', 'RESERVED' ou 'RENTED'");
        }
        return repository.save(novoVeiculo);
    }

    public List<Veiculo> listarTodos(){
        List<Veiculo> veiculos = repository.findAll();
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos cadastrados") ;
        }
        return veiculos;
    }

    public List<Veiculo> pesquisarPorNome(String nome) {
        List<Veiculo> veiculos = repository.findByNameIgnoreCase(nome);
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos com o nome informado") ;
        }
        return veiculos;
    }
    public List<Veiculo> pesquisarPorFabricante(String fabricante) {
        List<Veiculo> veiculos = repository.findByManufacturerIgnoreCase(fabricante);
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos com o fabricante informado") ;
        }
        return veiculos;
    }
    public List<Veiculo> pesquisarPorAno(Integer ano){
        List<Veiculo> veiculos = repository.findByAno(ano);
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos com o ano de fabricação informado");
        }
        return veiculos;
    }
}
