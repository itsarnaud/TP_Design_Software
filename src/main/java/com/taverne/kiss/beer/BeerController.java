package com.taverne.kiss.beer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beer")
public class BeerController {

    @PostMapping("/recommend")
    public ResponseEntity<String> recommend(@RequestBody BeerPreference preference) {
        IBeerSelector selector = BeerSelectorFactory.build();
        return ResponseEntity.ok(selector.selectBeer(preference));
    }
}
