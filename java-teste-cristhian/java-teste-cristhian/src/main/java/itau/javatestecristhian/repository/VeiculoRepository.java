package itau.javatestecristhian.repository;

import itau.javatestecristhian.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByChassi(String chassi);
    Optional<Veiculo> findByPlaca(String placa);
    @Query("SELECT v FROM Veiculo v ORDER BY v.manufacturer, v.name, v.ano")
    List<Veiculo> listagemOrdenada();
    @Query("SELECT v FROM Veiculo v WHERE v.name = :nome ORDER BY v.manufacturer, v.name, v.ano")
    List<Veiculo> pesquisaPorNomeOrdenada(String nome);
    @Query("SELECT v FROM Veiculo v WHERE v.manufacturer = :fabricante ORDER BY v.manufacturer, v.name, v.ano")
    List<Veiculo> pesquisaPoFabricanteOrdenada(String fabricante);
    @Query("SELECT v FROM Veiculo v WHERE v.ano = :ano ORDER BY v.manufacturer, v.name, v.ano")
    List<Veiculo> pesquisaPorAnoOrdenada(Integer ano);


}
