package com.gtbr.hexapi.controller;

import com.gtbr.hexapi.entity.Match;
import com.gtbr.hexapi.record.MatchRecord;
import com.gtbr.hexapi.record.MatchScoreBoardRecord;
import com.gtbr.hexapi.record.UserPersonalHighscoresRecord;
import com.gtbr.hexapi.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Match> findMatchById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(matchService.findById(uuid));
    }

    @GetMapping("/watched/{uuid}")
    public ResponseEntity<Match> watchedAd(@PathVariable UUID uuid) {
        return ResponseEntity.accepted().body(matchService.watchedAd(uuid));
    }

    @GetMapping("/personal/{uuid}")
    public ResponseEntity<UserPersonalHighscoresRecord> findPersonalHighScores(@PathVariable UUID uuid){
        return ResponseEntity.ok(matchService.findUserPersonalHighscore(uuid));
    }

    @GetMapping
    public ResponseEntity<List<MatchScoreBoardRecord>> findMatches(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                            @RequestParam String mode) {
        return ResponseEntity.ok(matchService.findMatchList(mode, size));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Match>> findMatchByUser(@PathVariable UUID userId,
                                                       @RequestParam(required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                       @RequestParam(required = false) String mode) {
        return ResponseEntity.ok(matchService.findByUserId(userId, mode, page, pageSize));
    }

    @PostMapping
    public ResponseEntity<Match> registerMatch(@RequestBody MatchRecord match){
        return ResponseEntity.created(URI.create("")).body(matchService.registerMatch(match));
    }
}
