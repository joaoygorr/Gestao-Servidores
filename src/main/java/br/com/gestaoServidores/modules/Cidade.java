package br.com.gestaoServidores.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    private Long id;

    @Column(name = "cid_nome", nullable = false, length = 200, unique = true)
    private String nome;

    @Column(name = "cid_uf", nullable = false, length = 2)
    private String uf;

    public Cidade(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
    }
}
