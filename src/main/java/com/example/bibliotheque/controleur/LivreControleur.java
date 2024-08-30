package com.example.bibliotheque.controleur;

import com.example.bibliotheque.entity.Livre;
import com.example.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreControleur {

    @Autowired
    private LivreRepository livreRepository;

    // Récupérer tous les livres
    @GetMapping
    public List<Livre> getTousLesLivres() {
        return livreRepository.findAll();
    }

    // Récupérer un livre par ID
    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreParId(@PathVariable Long id) {
        return livreRepository.findById(id)
                .map(livre -> ResponseEntity.ok().body(livre))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Ajouter un nouveau livre
    @PostMapping
    public Livre creerLivre(@RequestBody Livre livre) {
        return livreRepository.save(livre);
    }

    // Mettre à jour un livre existant
    @PutMapping("/{id}")
    public ResponseEntity<Livre> mettreAJourLivre(@PathVariable Long id, @RequestBody Livre detailsLivre) {
        return livreRepository.findById(id)
                .map(livre -> {
                    livre.setTitre(detailsLivre.getTitre());
                    livre.setAuteur(detailsLivre.getAuteur());
                    livre.setIsbn(detailsLivre.getIsbn());
                    livre.setResume(detailsLivre.getResume());
                    livre.setDatePublication(detailsLivre.getDatePublication());
                    Livre livreMisAJour = livreRepository.save(livre);
                    return ResponseEntity.ok().body(livreMisAJour);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Supprimer un livre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerLivre(@PathVariable Long id) {
        return livreRepository.findById(id)
                .map(livre -> {
                    livreRepository.delete(livre);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
