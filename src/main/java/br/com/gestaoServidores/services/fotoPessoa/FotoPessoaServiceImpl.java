package br.com.gestaoServidores.services.fotoPessoa;

import br.com.gestaoServidores.modules.FotoPessoa;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.record.fotoPessoa.FotoPessoaDTO;
import br.com.gestaoServidores.repositories.FotoPessoaRepository;
import br.com.gestaoServidores.services.pessoa.PessoaService;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FotoPessoaServiceImpl implements FotoPessoaService {
    private static final String BUCKET_NAME = "images";

    private final FotoPessoaRepository fotoPessoaRepository;

    private final MinioClient minioClient;

    private final PessoaService pessoaService;

    @Override
    public List<FotoPessoa> upload(FotoPessoaDTO dto) throws Exception {
        Pessoa pessoa = this.pessoaService.findByPerson(dto.pessoa());

        List<FotoPessoa> fotoPessoas = new ArrayList<>();
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        }

        for (MultipartFile image : dto.images()) {
            InputStream fileStream = image.getInputStream();
            String objectId = UUID.randomUUID().toString();

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectId)
                    .stream(fileStream, fileStream.available(), -1)
                    .contentType(image.getContentType())
                    .build();

            ObjectWriteResponse res = minioClient.putObject(putObjectArgs);

            FotoPessoa fotoPessoa = new FotoPessoa(pessoa, LocalDate.now(), res.bucket(), objectId);
            fotoPessoas.add(this.fotoPessoaRepository.save(fotoPessoa));
        }
        return fotoPessoas;
    }

    @Override
    public String getImageByhash(String hash) throws Exception  {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(BUCKET_NAME)
                        .object(hash)
                        .expiry(300)
                        .build()
        );
    }
}
