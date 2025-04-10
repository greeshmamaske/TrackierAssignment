package com.bookmanagement.book_manager.controller;

import com.bookmanagement.book_manager.util.AESUtil;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @PostMapping("/encrypt")
    public ResponseEntity<?> encryptNote(@RequestBody NoteRequest noteRequest) {
        try {
            String encrypted = AESUtil.encrypt(noteRequest.getText());
            return ResponseEntity.ok(new NoteResponse(encrypted));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Encryption failed: " + e.getMessage());
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decryptNote(@RequestBody NoteRequest noteRequest) {
        try {
            String decrypted = AESUtil.decrypt(noteRequest.getText());
            return ResponseEntity.ok(new NoteResponse(decrypted));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Decryption failed: " + e.getMessage());
        }
    }

    @Data
    static class NoteRequest {
        private String text;
    }

    @Data
    static class NoteResponse {
        private final String result;
    }

}
