package org.example.score.repository;

import org.example.score.UserScore;

import java.io.IOException;
import java.util.Optional;

public interface ScoreRepository {
    public Optional<UserScore> getMaxScore() throws IOException;
    public void addNewScore(UserScore score) throws IOException;
}
