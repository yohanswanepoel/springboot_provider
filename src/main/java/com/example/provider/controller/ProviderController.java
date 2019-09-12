package com.example.provider.controller;

import com.example.provider.exception.ResourceNotFoundException;
import com.example.provider.model.Provider;
import com.example.provider.repository.ProviderRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Provider Management APIs", description = "Operations to manage providers and their onboarding status")
public class ProviderController {
    
    @Autowired
    private ProviderRepository providerRepository;


    @GetMapping("/health")
    public String demo() {
        System.out.println("Healthy");
        return "{\"status\":\"healthy\"}";
    }

    @GetMapping("/providers/count/{status}")
    public String countByStatus(
        @ApiParam(value = "Provider status", required = true)
        @PathVariable(value = "status") String status){
            long count = providerRepository.countByStatus(status);
            String resp = "{'status':" + new Long(count).toString() + "}";
            return resp;
        }
    /**
     * Get all providers list
     */
    @ApiOperation(value = "View a list of available providers", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/providers")
    public List<Provider> getAllProviders(){
        return providerRepository.findAll();
    }

    /**
     * get providers by id
     */
    @ApiOperation(value = "Get provider by ID")
    @GetMapping("/providers/{id}")
    public ResponseEntity<Provider> getProviderById(
        @ApiParam(value = "Provider id from which provider object will retrieved", required = true)
        @PathVariable(value = "id") Long providerId)
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
    @ApiOperation(value = "Add a provider")
    @PostMapping("/providers")
    public Provider createProvider(
        @ApiParam(value = "Provider object store in database table", required = true)
        @Valid @RequestBody Provider provider) {
        return providerRepository.save(provider);
    }

    @ApiOperation(value = "Delete a provider a provider")
    @DeleteMapping("/providers/{id}")
    public Map<String, Boolean> deleteProvider(@PathVariable(value = "id") Long providerId) throws Exception {
        Provider provider =
            providerRepository
                .findById(providerId)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found on :: " + providerId));
        providerRepository.delete(provider);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}