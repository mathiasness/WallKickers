package no.uib.inf101.sem2.wallKickers.model;

import java.io.BufferedReader;
import java.io.FileWriter;  
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Class handles the highscore of the game, reads and writes the the file "HighScore.txt", updating the highscore
 */
public class HighScoreHandler {
    private int score;

    public HighScoreHandler(int score) {
        this.score = score;
    }

    int scoreHandler() {
        int highScore = 0;
        List<String> listOfScores;
        try{
            listOfScores = readFile();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        int scoreInList = Integer.parseInt(listOfScores.get(0));
        if (scoreInList > highScore) highScore = scoreInList;
        if (score > highScore) {
            writeToFile(score);
            highScore = score;
        }

        return highScore;
    }
    
    private List<String> readFile() throws IOException {
        BufferedReader reader = new BufferedReader(
        new InputStreamReader(
            HighScoreHandler.class.getResourceAsStream("/HighScore.txt"), StandardCharsets.UTF_8));

        List<String> words = reader.lines().toList();
        reader.close();
        return words;
    }

    private void writeToFile(int newHighScore) {
        String newScore = Integer.toString(newHighScore);
        try {
            FileWriter scoreWriter = new FileWriter(System.getProperty("user.dir")+"/src/main/resources/HighScore.txt");
            scoreWriter.write(newScore); 
            scoreWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
