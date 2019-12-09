package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.Grass;
import com.mpi.witcher.server.models.requests.AddProducableItemRequest;
import com.mpi.witcher.server.models.requests.CookRequest;
import com.mpi.witcher.server.models.requests.ConsumptionRequest;
import com.mpi.witcher.server.repositories.GoodsRepository;
import com.mpi.witcher.server.repositories.GrassRepository;
import com.mpi.witcher.server.repositories.RecipesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

    private final GoodsRepository goodsRepository = new GoodsRepository();


    @GetMapping("/recipes")
    public ResponseEntity  GetAllRecipes() {
        return ok(RecipesRepository.Recipes);
    }

    @PostMapping("/recipes")
    public ResponseEntity addRecipe(@RequestBody AddProducableItemRequest addRecipeRequest) {
        if(goodsRepository.addProducableItem(addRecipeRequest)) {
            return ok(null);
        } else {
            return badRequest().build();
        }
    }

    @PostMapping("/recipes/cook")
    public ResponseEntity CookPotionByRecipe(@RequestBody CookRequest cookRequest) {
        goodsRepository.produceRecipe(cookRequest.getRecipeId());
        return ok(null);
    }

    @GetMapping("/grass")
    public ResponseEntity GetAllGrass() {
        return ok(GrassRepository.Grass);
    }

    @PostMapping("/consumpt") //TODO сделать для всех ресурсов в зависимотси от типа request.type = animals, grass, runes
    public ResponseEntity changeAmount(@RequestBody ConsumptionRequest request) {
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

    @GetMapping("/runes")
    public ResponseEntity getAllRunes() {
        //TODO возвращать руны
        /*
        * [{
        *       id: 12,
        *       name: "Руна счастья",
        *       category; "Редкие",
        *       enchanted: true
        *  }]
        * */
        return ok(null);
    }

    @PostMapping("/runes/enchant")
    public ResponseEntity enchantRune(@RequestBody Object request) {
        //TODO переводить статус руны в зачарованные (enchanted: true),
        // параметр
        /*
        * {
        *   runeId: 12
        * }
        * */
        return ok(null);
    }

    @GetMapping("/animals")
    public ResponseEntity getAllAnimals() {
        // TODO вернуть список животных ресурсов
        /*
        * [{
        *   id: 2,
        *   name: "Noga Zver",
        *   description: "Ogramnaya lapa s kogtyami",
        *   quantity: 12
        * } ]
        *
        *
        * */
        return ok(null);
    }

    @GetMapping("/info")
    public ResponseEntity getResourceInfo(@RequestParam String type, @RequestParam long id) {
        // TODO получить имя ресурса, описание, количество и историю добычи
        // Параметры: type- тип ("grass","runes", "animals", "receipts  "), id - ид ресурса
        /*
        *
        *
        * {
              name: "Драконьи слезы",
              description: "Слезы страшного дракона",
              quantity: 5,
              history: [{
                  name: 'Иванов Сергей Михайлович',
                  quantity: 5,
                  date: "01.10.2019"}]
           }
        * */
        return ok(null);
    }
}
