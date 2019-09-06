package com.example.provider.controller;

import com.example.provider.exception.ResourceNotFoundException;
import com.example.provider.model.Provider;
import com.example.provider.repository.ProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProviderController {
    
    @Autowired
    private ProviderRepository providerRepository;

    /**
     * Get all providers list
     */
    @GetMapping("/providers")
    public List<Provider> getAllProviders(){
        return providerRepository.findAll();
    }

    /**
     * get providers by id
     */
    @GetMapping("/providers/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable(value = "id") Long providerId)
        throws ResourceNotFoundException {
            Provider provider = 
                providerRepository
                    .findById(providerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Provider Not Found"));
            return ResponseEntity.ok().body(provider);
    }

    /**
   * Create provider.
   */
    @PostMapping("/providers")
    public Provider createProvider(@Valid @RequestBody Provider provider) {
        return providerRepository.save(provider);
    }


}