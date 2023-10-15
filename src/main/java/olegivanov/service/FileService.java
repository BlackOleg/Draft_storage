package olegivanov.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import olegivanov.dtos.FileResponse;
import olegivanov.entities.File;
import olegivanov.entities.User;
import olegivanov.exceptions.InputDataException;
import olegivanov.exceptions.UnauthorizedException;
import olegivanov.repositories.FileRepository;
import olegivanov.repositories.UserRepository;
import olegivanov.token.TokenRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class FileService {

    FileRepository fileRepository;
    TokenRepository tokenRepository;
    UserRepository userRepository;

    public void uploadFile(String authToken, String filename, MultipartFile file) throws IOException {
        final User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedException("Unauthorized error");
        }
        fileRepository.save(new File(filename, file.getSize(), file.getContentType(), file.getBytes(), user));
        log.info("User {} upload file {}", user.getEmail(), filename);
    }

    public void deleteFile(String authToken, String filename) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Delete file error");
            throw new UnauthorizedException("Unauthorized error");
        }
        log.info("User {} delete file {}", user.getEmail(), filename);
        fileRepository.removeByUserAndFilename(user, filename);
    }

    public File downloadFile(String authToken, String filename) {
        final User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedException("Unauthorized error");
        }
        final File file = fileRepository.findByUserAndFilename(user, filename);
        if (file == null) {
            log.error("Download file error");
            throw new InputDataException("Error input data");
        }
        log.info("User {} download file {}", user.getEmail(), filename);
        return file;
    }

    public void editFileName(String authToken, String filename, String newFileName) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Edit file error");
            throw new UnauthorizedException("Unauthorized error");
        }
        if (newFileName != null) {
            fileRepository.editFileNameByUser(user, filename, newFileName);
            log.info("User {} edit file {}", user.getEmail(), filename);
        } else {
            throw new InputDataException("Error input data");
        }
    }

    public List<FileResponse> getAllFiles(String authToken, Integer limit) {
        final User user = getUser(authToken);
        if (user == null) {
            log.error("Get all files error");
            throw new UnauthorizedException("Unauthorized error");
        }
        log.info("User {} get all files", user.getEmail());
        return fileRepository.findAllByUser(user, Sort.by("filename")).stream()
                .map(f -> new FileResponse(f.getFilename(), f.getSize()))
                .collect(Collectors.toList());
    }

    private User getUser(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        Optional<User> user = tokenRepository.findUserByToken(authToken);
        final String userEmail = user.isPresent() ? user.get().getEmail() : null;
        return userRepository.findByEmail(userEmail)
              .orElseThrow(() -> new UnauthorizedException("Unauthorized error"));

    }
//    private User getUser(String authToken) {
//        if (authToken.startsWith("Bearer ")) {
//            authToken = authToken.substring(7);
//        }
//        final String username = authorizationRepository.getUserNameByToken(authToken);
//        return userRepository.findByLogin(username)
//                .orElseThrow(() -> new UnauthorizedException("Unauthorized error"));
//    }
}
