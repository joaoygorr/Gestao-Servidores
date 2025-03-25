package br.com.gestaoServidores.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FotoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "fp_data", nullable = false)
    private LocalDate data;

    @Column(name = "fp_bucket", nullable = false, length = 50)
    private String bucket;

    @Column(name = "ft_hash", nullable = false, length = 50)
    private String hash;

    public FotoPessoa(Pessoa pessoa, LocalDate data, String bucket, String hash) {
        this.pessoa = pessoa;
        this.data = data;
        this.bucket = bucket;
        this.hash = hash;
    }
}
