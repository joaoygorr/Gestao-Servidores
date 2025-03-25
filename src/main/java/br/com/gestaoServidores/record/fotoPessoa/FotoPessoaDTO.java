package br.com.gestaoServidores.record.fotoPessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record FotoPessoaDTO(@Schema(hidden = true) Long id,
                            @NotNull(message = "Pessoa não pode ser nula") Long pessoa,
                            @NotNull(message = "Foto não pode ser nula") List<MultipartFile> images,
                            @Schema(hidden = true) LocalDate data,
                            @Schema(hidden = true) String bucket,
                            @Schema(hidden = true) String hash) {
}
