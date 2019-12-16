package com.mpi.witcher.server.controllers;

import com.mpi.witcher.server.models.Recipe;
import com.mpi.witcher.server.models.requests.AddProducableItemRequest;
import com.mpi.witcher.server.models.requests.CookRequest;
import com.mpi.witcher.server.models.requests.ConsumptionRequest;
import com.mpi.witcher.server.repositories.GoodsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

    private final GoodsRepository goodsRepository = new GoodsRepository();


    @GetMapping("/recipes")
    public ResponseEntity  GetAllRecipes() {
        List<Recipe> recipes = goodsRepository.getRecipes();
        return ok(recipes);
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
        return ok(goodsRepository.getProductsByCategory("grass"));
    }

    @PostMapping("/consumpt") //TODO сделать для всех ресурсов в зависимотси от типа request.type = animals, grass, runes
    public ResponseEntity changeAmount(@RequestBody ConsumptionRequest request) {
        try {
            goodsRepository.updateGoodsQuantity(request.getId(), request.getAmount());
        } catch (SQLException e)
        {
            return status(500).build();
        }
        return ok(null);
    }

    @GetMapping("/runes")
    public ResponseEntity getAllRunes() {
        return ok(goodsRepository.getProductsByCategory("runes"));
    }

    @PostMapping("/runes/enchant")
    public ResponseEntity enchantRune(@RequestParam int id) {
        goodsRepository.produceRecipe(id);
        return ok(null);
    }

    @GetMapping("/animals")
    public ResponseEntity getAllAnimals() {
        return ok(goodsRepository.getProductsByCategory("animals"));
    }

    @GetMapping("/info")
    public ResponseEntity getResourceInfo(@RequestParam String type, @RequestParam int id) {
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
        return ok(goodsRepository.getById(id));
    }
}
