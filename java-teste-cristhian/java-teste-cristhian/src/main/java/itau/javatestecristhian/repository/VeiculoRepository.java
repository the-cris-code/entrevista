package itau.javatestecristhian.repository;

import itau.javatestecristhian.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByChassi(String chassi);
    Optional<Veiculo> findByPlaca(String placa);

    List<Veiculo> findAllOrderByManufacturer();
    List<Veiculo> findByNameIgnoreCase(String nome);
    List<Veiculo> findByAno(Integer ano);
    List<Veiculo> findByManufacturerIgnoreCase(String fabricante);

}
