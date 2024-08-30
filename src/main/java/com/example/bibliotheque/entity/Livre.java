// src/main/java/com/example/bibliotheque/entite/Livre.java
package com.example.bibliotheque.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Cette annotation indique que cette classe sera mappée à une table de la base de données
@Data // Lombok générera automatiquement les getters, setters, toString, etc.
public class Livre {

    @Id // Indique que ce champ est la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indique que l'ID sera généré automatiquement
    private Long id;

    private String titre;
    private String auteur;
    private String isbn;
    private String resume;
    private String datePublication;
}
