package com.luizalabs.wishlist.controller;

import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.response.WishlistDTO;
import com.luizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<WishlistDTO> toAdd(@PathVariable String id) {

        var wishlist = wishlistService.add(id);

        return ResponseEntity.ok().body(new WishlistDTO(wishlist));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<WishlistDTO> delete(@PathVariable String id) {

        var wishlist = wishlistService.deleteById(id);

        return ResponseEntity.ok().body(new WishlistDTO(wishlist));
    }

    @GetMapping
    public ResponseEntity<WishlistDTO> getAll() {

        var wishlist = wishlistService.getAll();

        return ResponseEntity.ok().body(new WishlistDTO(wishlist));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistDTO> getById(@PathVariable String id) {

        var wishlist = wishlistService.getById(id);

        return ResponseEntity.ok().body(new WishlistDTO(wishlist));
    }
}