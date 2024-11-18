package guru.springframework.domain;

import jakarta.persistence.Entity;

@Entity
public class Notes {

    private Recipe recipe;
    private String recipeNotes;
}
