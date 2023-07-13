package com.vanchondo.secret;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Log4j2
public class SecretController {

    private final StringEncryptor encryptor;

    @GetMapping(value="encrypt")
    public ResponseEntity<String> encrypt(@RequestParam(name="value") String value) {
        log.info("::encrypt::value={}", value);
        return ResponseEntity.ok(encryptor.encrypt(value));
    }

    @GetMapping(value="decrypt")
    public ResponseEntity<String> decrypt(@RequestParam(name="value") String value) {
        log.info("::decrypt::value={}", value);
        return ResponseEntity.ok(encryptor.decrypt(value));
    }
}