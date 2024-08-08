package com.luizalabs.wishlist.controller;

import com.luizalabs.wishlist.entity.WishlistEntity;
import com.luizalabs.wishlist.repository.WishlistRepository;
import com.luizalabs.wishlist.request.WishlistRequestDTO;
import com.luizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistService wishlistService;

    @PostMapping()
    public WishlistEntity toAdd(@RequestBody WishlistRequestDTO wishlistRequestDTO) {

        System.out.println(wishlistRequestDTO);

        return this.wishlistService.add(wishlistRequestDTO);
    }
}