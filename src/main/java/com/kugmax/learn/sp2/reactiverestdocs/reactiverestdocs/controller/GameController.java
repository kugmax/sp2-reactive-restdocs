package com.kugmax.learn.sp2.reactiverestdocs.reactiverestdocs.controller;

import com.kugmax.learn.sp2.reactiverestdocs.reactiverestdocs.model.Game;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/GameAPI")
@RestController
public class GameController {

    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public Mono<Game> getGame(@PathVariable("id") long id ) {
        Game game = new Game(id, "Nice", "Game");
        System.out.println("### game " + game);

        return Mono.just(game);
    }

    @RequestMapping(value = "/game/", method = RequestMethod.PUT)
    public Mono<Void> save(@RequestBody Game game) {
        System.out.println("### game " + game);

        return Mono.empty();
    }
}
