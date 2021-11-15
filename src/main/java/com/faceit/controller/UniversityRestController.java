package com.faceit.controller;

import com.faceit.model.University;
import com.faceit.repo.UniversityRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@RestController
public class UniversityRestController {

    private final UniversityRepository universityRepository;

    public UniversityRestController(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @GetMapping
    public List<University> getList(@RequestParam Optional<Integer> pageNo,
                                    @RequestParam Optional<Integer> pageSize) {

        if (pageNo.isPresent() || pageSize.isPresent()){
            PageRequest pageable = PageRequest.of(pageNo.orElse(0), pageSize.orElse(5));
            Page<University> posts = universityRepository.findAll(pageable);

            return posts.getContent();
        }

        return universityRepository.findAll();
    }

    @PostMapping("/upload")
    public String uploadDate(@RequestParam("file") MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withDelimiter(';')
                .parse(reader);

        for (CSVRecord record : records) {
            University university = new University();
            university.setState(record.get("State"));
            university.setName(record.get("Name"));
            university.setInstitutionType(record.get("Institution Type"));
            university.setPhone(record.get("Phone Number"));
            university.setWebsite(record.get("Website"));
            universityRepository.save(university);
        }

        return "uploaded";
    }
}
