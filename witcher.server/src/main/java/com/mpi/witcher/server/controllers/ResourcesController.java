package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.Grass;
import com.mpi.witcher.server.models.Requests.CookRequest;
import com.mpi.witcher.server.models.Requests.GrassConsumptionRequest;
import com.mpi.witcher.server.repositories.GrassRepository;
import com.mpi.witcher.server.repositories.RecipesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {
    @GetMapping("/recipes")
    public ResponseEntity  GetAllRecipes() {
        return ok(RecipesRepository.Recipes);
    }

    @PostMapping("/recipes/cook")
    public ResponseEntity CookPotionByRecipe(@RequestBody CookRequest cookRequest) {
        //TODO увеличить количество
        System.out.println(cookRequest.getRecipeId());
        return ok(null);
    }

    @GetMapping("/grass")
    public ResponseEntity GetAllGrass() {
        return ok(GrassRepository.Grass);
    }

    @PutMapping("/grass")
    public ResponseEntity changeAmountGrass(@RequestBody GrassConsumptionRequest request) {
        try {
            Grass foundGrass = GrassRepository.Grass.stream().filter(g -> g.getId() == request.getId())
                    .findFirst()
                    .orElseThrow(() -> new Exception("Grass not found"));
            if(request.getIsPlus()){
                foundGrass.increaseQuantity(request.getAmount());
            } else if(foundGrass.getQuantity() > 0){
                foundGrass.reduceQuantity(request.getAmount());
            }
//            if(foundGrass.getQuantity() == 0)
//                GrassRepository.Grass.remove(foundGrass);
            return ok(foundGrass);
        } catch (Exception e) {
            return badRequest().body(e.getMessage());
        }
    }
}
