package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (eachUomOptional.isEmpty()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (tablespoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (teaspoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (pinchUomOptional.isEmpty()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (cupUomOptional.isEmpty()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (pintUomOptional.isEmpty()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        Optional<UnitOfMeasure> sliceUomOptional = unitOfMeasureRepository.findByDescription("Slice");
        if (sliceUomOptional.isEmpty()) {
            throw new RuntimeException("Expected unit of measure not found");
        }

        //get UOM optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoon = tablespoonUomOptional.get();
        UnitOfMeasure teaspoon = teaspoonUomOptional.get();
        UnitOfMeasure pinch = pinchUomOptional.get();
        UnitOfMeasure cup = cupUomOptional.get();
        UnitOfMeasure pint = pintUomOptional.get();
        UnitOfMeasure slice = sliceUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (italianCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        if (fastFoodCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected category not found");
        }

        //get category optionals
        Category americanCategory = americanCategoryOptional.get();
        Category italianCategory = italianCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();
        Category fastFoodCategory = fastFoodCategoryOptional.get();

        //Perfect Guac Recipe
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("How to Make Perfect Guacamole");
        guacRecipe.setPrepTime("10 minutes");
        guacRecipe.setCookTime("0 minutes");
        guacRecipe.setServings("2-4 servings");
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setSource("Simply Recipes");
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setDirections(
                """
                        1. Cut the avocados:
                        Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and \
                        scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\
                        2. Mash the avocado flesh:
                        Using a fork, roughly mash the avocado. Don't overdo it! The guacamole should be a little chunky.\
                        3. Add the remaining ingredients to taste:
                        Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some \
                        balance to the richness of the avocado and will help delay the avocados from turning brown.
                        
                        Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. \
                        So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.
                        
                        Remember that much of this is done to taste because of the variability in the fresh ingredients. \
                        Start with this recipe and adjust to your taste.\
                        4. Serve immediately:
                        If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down \
                        to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn \
                        the guacamole brown.)
                        
                        Garnish with slices of red radish or jicama strips. Serve with your choice of store-bought \
                        tortilla chips or make your own homemade tortilla chips.
                        
                        Refrigerate leftover guacamole up to 3 days.
                        
                        Note: Chilling tomatoes dulls their flavor. So, if you want to add chopped tomato to your \
                        guacamole, add just before serving.""");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Note: Be careful handling chilis! If using, it's best to wear food-safe gloves. If no " +
                "gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal("2"), eachUom));
        guacRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal("0.25"), teaspoon));
        guacRecipe.addIngredient(new Ingredient("lime or lemon juice", new BigDecimal("1"), tablespoon));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal("2"), slice));
        guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems)", new BigDecimal("2"), tablespoon));
        guacRecipe.addIngredient(new Ingredient("freshly ground pepper", new BigDecimal("1"), pinch));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, chopped (optional)", new BigDecimal("0.5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);

        //Chicken Tacos
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Quick and Easy Buffalo Chicken Tacos");
        tacoRecipe.setPrepTime("15 minutes");
        tacoRecipe.setCookTime("2 minutes");
        tacoRecipe.setServings("4-6 servings");
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setSource("Simply Recipes");
        tacoRecipe.setUrl("https://www.simplyrecipes.com/buffalo-chicken-tacos-recipe-7567648");
        tacoRecipe.setDirections("""
                1. Make the Buffalo chicken:
                Microwave the butter in a large microwave-safe bowl in 4 to 6 (10-second) bursts until just melted. \
                Add the hot sauce and garlic powder and whisk until combined. Add the shredded chicken and toss to coat.\s
                
                Alternatively, melt the butter in a small saucepan over medium-low heat. Turn off the heat and add the \
                hot sauce and garlic powder, then transfer to a large bowl and combine with the chicken.\
                2. Make the coleslaw:
                Place the Greek yogurt or sour cream, lime juice, olive oil, salt, and pepper in a large bowl and whisk \
                to combine. Add the coleslaw mix and toss to coat.
                
                3. Warm the tortillas:\s
                Stack the tortillas on a microwave-safe plate and cover with a damp paper towel. Microwave until warm, \
                30 to 45 seconds. Wrap the stack in a clean kitchen towel or aluminum foil to keep warm until ready to use.\
                4. Assemble the tacos:
                To assemble the tacos, top the warmed tortillas with coleslaw. Top with the buffalo chicken and garnish \
                with the scallions and blue cheese.""");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("This recipe is all about leaning into conveniences. It’s a dinner I turn to when I " +
                "crave something familiar and comforting but also feel a bit lazy (which, if I am being honest, happens often).");
        tacoNotes.setRecipe(tacoRecipe);
        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("unsalted butter", new BigDecimal("6"), tablespoon));
        tacoRecipe.addIngredient(new Ingredient("hot sauce", new BigDecimal("0.5"), cup));
        tacoRecipe.addIngredient(new Ingredient("large plain rotisserie chicken, shredded (about 4 packed cups)", new BigDecimal("0.5"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("whole milk plain Greek yogurt or sour cream", new BigDecimal("3"), tablespoon));
        tacoRecipe.addIngredient(new Ingredient("freshly squeezed lime juice", new BigDecimal("2"), tablespoon));
        tacoRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal("1"), tablespoon));
        tacoRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal("0.5"), teaspoon));
        tacoRecipe.addIngredient(new Ingredient("freshly ground black pepper", new BigDecimal("0.25"), teaspoon));
        tacoRecipe.addIngredient(new Ingredient("coleslaw mix", new BigDecimal("4"), cup));
        tacoRecipe.addIngredient(new Ingredient("(6-inch) flour tortillas", new BigDecimal("12"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("green onions, thinly sliced", new BigDecimal("2"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("blue cheese, crumbled (about 4 oz)", new BigDecimal("1"), cup));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);

        return recipes;
    }
}
