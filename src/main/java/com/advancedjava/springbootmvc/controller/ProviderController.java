package com.advancedjava.springbootmvc.controller;

import com.advancedjava.springbootmvc.entity.Provider;
import com.advancedjava.springbootmvc.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    ProviderRepository providerRepository;

    @GetMapping("")
    public List<Provider> findAll() {
        return providerRepository.findAll();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Provider> findOne(@PathVariable int id) {
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        Provider provider = optionalProvider.isPresent()?optionalProvider.get():null;
        HttpStatus status;
        if (provider == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(provider, status);

    }
    @GetMapping("/fake")
    public List<Provider> findAllFake() {
        List<Provider> providers = new ArrayList<>();
        providers.add(new Provider("provider1"));
        providerRepository.save(new Provider("provider1"));
        return providers;

    }
    @PostMapping("/add")
    public void add(@RequestBody @Valid Provider provider) {
        providerRepository.save(provider);
    }
    @PutMapping("/edit/{id}")
    public void edit(@RequestBody @Valid Provider provider, @PathVariable int id) throws Exception {
        if (id != provider.getId()) {
            throw new Exception("incoherence between ids");
        }
        providerRepository.save(provider);
    }

    @PatchMapping("/patch/{id}")
    public void editPatch(@RequestBody Map<String, Object> fields, @PathVariable int id) throws Exception {
        if (!providerRepository.findById(id).isPresent()) {
            throw new Exception("provider not found");
        }
        Provider provider = providerRepository.findById(id).get();
        if(id!=provider.getId() ) {
            throw new Exception("incoherence between ids");
        }

        fields.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(Provider.class, key);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, provider, value);
        });
        providerRepository.save(provider);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        providerRepository.deleteById(id);
    }

}
