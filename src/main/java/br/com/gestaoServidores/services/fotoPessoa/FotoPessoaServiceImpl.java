package br.com.gestaoServidores.services.fotoPessoa;

import br.com.gestaoServidores.modules.FotoPessoa;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.record.fotoPessoa.FotoPessoaDTO;
import br.com.gestaoServidores.repositories.FotoPessoaRepository;
import br.com.gestaoServidores.services.pessoa.PessoaService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FotoPessoaServiceImpl implements FotoPessoaService {
    private static final String BUCKET_NAME = "images";

    private final FotoPessoaRepository fotoPessoaRepository;

    private final MinioClient minioClient;

    private final PessoaService pessoaService;

    @Override
    public FotoPessoa upload(FotoPessoaDTO dto) throws Exception {
        Pessoa pessoa = this.pessoaService.findByPerson(dto.pessoa());

        InputStream fileStrem = dto.image().getInputStream();
        String fileName = dto.image().getOriginalFilename();

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        }

        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object(fileName)
                .stream(fileStrem, fileStrem.available(), -1)
                .contentType(dto.image().getContentType())
                .build();

        ObjectWriteResponse res = minioClient.putObject(putObjectArgs);

        return this.fotoPessoaRepository.save(new FotoPessoa(pessoa, LocalDate.now(), res.bucket(), res.etag()));
    }
}
