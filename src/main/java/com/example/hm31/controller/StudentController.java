package com.example.hm31.controller;

import com.example.hm31.model.Avatar;
import com.example.hm31.model.Student;
import com.example.hm31.service.AvatarService;
import com.example.hm31.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> get(@RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) Integer to,
                                                   @RequestParam(required = false) Integer from) {
        if (id != null) {
            return ResponseEntity.ok(Collections.singletonList(studentService.find(id)));
        }
        if (from != null || to != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(from, to));
        }
        return ResponseEntity.ok((List<Student>) studentService.getAll());
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping
    public Student set(@RequestBody Student student) {
        return studentService.set(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> remove(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }

        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping("/numberOfAllStudents")
    public ResponseEntity<Long> numberOfAllStudents() {
        return ResponseEntity.ok(studentService.numberOfAllStudents());
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> averageAge() {
        return ResponseEntity.ok(studentService.averageAge());
    }

    @GetMapping("/lastFiveStudent")
    public ResponseEntity<List<Student>> lastFiveStudent() {
        return ResponseEntity.ok(studentService.lastFiveStudent());
    }

    @GetMapping("/pagingAvatar/{page}/{size}")
    public ResponseEntity<List<Avatar>> AvatarPaging(@PathVariable Integer page,@PathVariable Integer size) {
        return ResponseEntity.ok(avatarService.paging(page, size));
    }
    public Collection<Student> getAll() {
        return studentService.getAll();
    }
    @GetMapping("/name/start-from-a")
    public List<String> startFromA() {
        return studentService.findAllStartFromA();
    }
    @GetMapping("/avg-age")
    public double avgAge() {
        return studentService.avgAge();
    }
    @GetMapping("/calculate")
    public int calculate() {
        return studentService.calculate();
    }
    @GetMapping("/print-names")
    public void printNames() {
        studentService.printStudentsName();
    }

    @GetMapping("/print-names-sync")
    public void printNamesSync() {
        studentService.printStudentsNameSync();
    }
}
