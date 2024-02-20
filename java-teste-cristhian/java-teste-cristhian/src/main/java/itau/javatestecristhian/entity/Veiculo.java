package itau.javatestecristhian.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "veiculo")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String chassi;

    @NotBlank
    private String name;

    @NotBlank
    private String manufacturer;

    private Integer ano;

    @NotBlank
    private String color;

    @NotBlank
    @Pattern(regexp = "ACTIVATED|DEACTIVATED|RESERVED|RENTED", message = "O status deve ser 'ACTIVATED'," +
            " 'DEACTIVATED', 'RESERVED' ou 'RENTED'")
    private String status;

    @NotBlank
    private String placa;
}
