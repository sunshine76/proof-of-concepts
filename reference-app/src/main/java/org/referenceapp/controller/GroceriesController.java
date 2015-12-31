package org.referenceapp.controller;

import org.referenceapp.entity.GroceryEntity;
import org.referenceapp.io.JsonResponse;
import org.referenceapp.repository.GroceryRepository;
import org.referenceapp.annotation.LogExecutionMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by schinta6 on 12/23/15.
 */
@RestController
@RequestMapping("/groceries")

public class GroceriesController {

    @Autowired
    private GroceryRepository groceryRepository;

    @LogExecutionMetrics
    @RequestMapping( value="/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse<List<GroceryEntity>>> getGroceryPrices(@PathVariable(value = "id") String id){
        JsonResponse<List<GroceryEntity>> jsonResponse = new JsonResponse<List<GroceryEntity>>();
        jsonResponse.setResult(groceryRepository.findAll());
        return new ResponseEntity<JsonResponse<List<GroceryEntity>>>(jsonResponse, HttpStatus.OK);

    }

    @LogExecutionMetrics
    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse<List<GroceryEntity>>> getGroceryPrices(){
        JsonResponse<List<GroceryEntity>> jsonResponse = new JsonResponse<List<GroceryEntity>>();
        jsonResponse.setResult(groceryRepository.findAll());
        return new ResponseEntity<JsonResponse<List<GroceryEntity>>>(jsonResponse, HttpStatus.OK);

    }
}
