package com.example.accounts.controller;

import com.example.accounts.dto.CustomerDetailsDto;
import com.example.accounts.dto.ErrorResponseDto;
import com.example.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name= "Rest api for Customer in EasyBank", description = "Rest api to FETCH customer details")
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }
    @Operation(
            summary = "Fetch CustomerDetails rest api",
            description = "RestApi to fetch customer, loans and Account inside EasyBank"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digit") @RequestParam String mobileNumber) {

        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);
    }
}
