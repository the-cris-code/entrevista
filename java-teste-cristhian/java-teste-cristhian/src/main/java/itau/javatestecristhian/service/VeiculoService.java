package itau.javatestecristhian.service;

import itau.javatestecristhian.entity.Veiculo;
import itau.javatestecristhian.exception.NoContentException;
import itau.javatestecristhian.exception.NotFoundException;
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
        List<Veiculo> veiculos = repository.listagemOrdenada();
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos cadastrados") ;
        }
        return veiculos;
    }

    public List<Veiculo> pesquisarPorNome(String nome) {
        List<Veiculo> veiculos = repository.pesquisaPorNomeOrdenada(nome);
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos com o nome informado") ;

        }
        return veiculos;
    }
    public List<Veiculo> pesquisarPorFabricante(String fabricante) {
        List<Veiculo> veiculos = repository.pesquisaPoFabricanteOrdenada(fabricante);
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos com o fabricante informado") ;
        }
        return veiculos;
    }
    public List<Veiculo> pesquisarPorAno(Integer ano){
        List<Veiculo> veiculos = repository.pesquisaPorAnoOrdenada(ano);
        if (veiculos.isEmpty()) {
            throw new NoContentException("Não há veiculos com o ano de fabricação informado");
        }
        return veiculos;
    }

    public Veiculo editarVeiculo(Integer id, Veiculo veiculoAtualizado){
        if(!verificaStatusCorreto(veiculoAtualizado.getStatus())){
            throw new StatusInvalidoException("O status deve ser 'ACTIVATED'," +
                    " 'DEACTIVATED', 'RESERVED' ou 'RENTED'");
        }

        Optional<Veiculo> veiculoEncontrado = repository.findById(id);

        if (veiculoEncontrado.isPresent()){
            veiculoAtualizado.setId(id);
            repository.save(veiculoAtualizado);
            return veiculoAtualizado;
        }else{
            throw new NotFoundException("Veiculo não encontrado, verifique o ID");
        }

    }


    public Veiculo alterarStatus(Integer id, String status){
        if(!verificaStatusCorreto(status)){
            throw new StatusInvalidoException("O status deve ser 'ACTIVATED'," +
                    " 'DEACTIVATED', 'RESERVED' ou 'RENTED'");
        }

        Optional<Veiculo> veiculoEncontrado = repository.findById(id);

        if (veiculoEncontrado.isPresent()){
            veiculoEncontrado.get().setStatus(status);
            return repository.save(veiculoEncontrado.get());
        }else{
            throw new NotFoundException("Veiculo não encontrado, verifique o ID");
        }
    }

    public String deletarPorId(Integer id){
        Optional<Veiculo> veiculo = repository.findById(id);

        if (veiculo.isEmpty()){
            throw new NotFoundException("Veiculo não encontrado");
        }else{
            repository.deleteById(id);
            return "Veiculo deletado com sucesso";
        }
    }

    public Veiculo pesquisarPorId(Integer id){
        Optional<Veiculo> veiculo = repository.findById(id);

        if (veiculo.isEmpty()){
            throw new NotFoundException("Veiculo não encontrado, verifique o ID");
        }

        return veiculo.get();
    }
}
