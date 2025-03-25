package br.com.gestaoServidores.services.fotoPessoa;

import br.com.gestaoServidores.modules.FotoPessoa;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.record.fotoPessoa.FotoPessoaDTO;
import br.com.gestaoServidores.repositories.FotoPessoaRepository;
import br.com.gestaoServidores.services.pessoa.PessoaService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
//
//        InputStream fileStrem = dto.image().getInputStream();
//        String fileName = dto.image().getOriginalFilename();
//
//        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
//        }
//
//        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
//                .bucket(BUCKET_NAME)
//                .object(fileName)
//                .stream(fileStrem, fileStrem.available(), -1)
//                .contentType(dto.image().getContentType())
//                .build();
//
//        ObjectWriteResponse res = minioClient.putObject(putObjectArgs);
        List<FotoPessoa> fotoPessoas = new ArrayList<>();
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        }
        for (MultipartFile image : dto.images()) {
            InputStream fileStream = image.getInputStream();
            String fileName = image.getOriginalFilename();

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(fileName)
                    .stream(fileStream, fileStream.available(), -1)
                    .contentType(image.getContentType())
                    .build();

            ObjectWriteResponse res = minioClient.putObject(putObjectArgs);

            FotoPessoa fotoPessoa = new FotoPessoa(pessoa, LocalDate.now(), res.bucket(), res.etag());
            fotoPessoas.add(this.fotoPessoaRepository.save(fotoPessoa));
        }
        return fotoPessoas;
    }
}
