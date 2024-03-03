package org.example.repo;

import java.io.*;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ScoreRepositoryFile {
    public record UserScore(String username, double score) {}
    public void addNewScore(UserScore score) throws IOException {
        FileWriter fileWriter = new FileWriter(
                Paths.get("src", "main", "java", "org", "example", "data", "scores.txt").toString(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(score.username);
        bufferedWriter.write(":");
        bufferedWriter.write(Double.toString(score.score));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        fileWriter.close();
    }
    public Optional<UserScore> getMaxScore() throws IOException {
        FileReader fileReader = new FileReader(
                Paths.get("src", "main", "java", "org", "example", "data", "scores.txt").toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Optional<UserScore> maxScorer = Stream.generate(() -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).takeWhile(Objects::nonNull).map(ele -> ele.split(":")).map(ele -> new UserScore(ele[0], Double.parseDouble(ele[1]))).max(
                Comparator.comparingDouble(UserScore::score));
        fileReader.close();
        return maxScorer;
    }
}
