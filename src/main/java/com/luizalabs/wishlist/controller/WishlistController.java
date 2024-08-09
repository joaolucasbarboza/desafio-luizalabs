package com.luizalabs.wishlist.controller;

import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{id}")
    public WishlistEntity toAdd(@PathVariable String id) {
        return wishlistService.add(id);
    }

    @DeleteMapping("/{id}")
    public WishlistEntity delete(@PathVariable String id) {
        return wishlistService.deleteById(id);
    }

    @GetMapping
    public WishlistEntity getAll() {
        return wishlistService.getAll();
    }
}