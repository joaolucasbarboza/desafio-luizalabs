package com.luizalabs.wishlist.controller;

import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.request.WishlistRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @PostMapping()
    @Transactional
    public ResponseEntity toAdd(@RequestBody @Valid WishlistRequestDTO data) {

        List<WishlistEntity> newProducts = wishlistRepository.findAllById(data.projectsId());

        return ResponseEntity.ok(newProducts);
    }
}