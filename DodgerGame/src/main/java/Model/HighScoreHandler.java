package Model; /**
 * @author Irja Vuorela
 * <p>
 * HighScoreHandler adds the player's score in a text file if it's among the top scores in that file.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles score keeping
 */
public class HighScoreHandler {

    private final String fileName = "src/main/resources/HighScores.txt"; // include the file extension
    private List<Integer> topScores = new ArrayList<>();
    private final int maxNrOfTopScores = 10; // Defined number of top scores
    private FileWriter writer;

    /**
     * Saves a player's score in a text file with the best times.
     *
     * @param score is the player's score
     */
    public void handleScore(int score) {
        createFile(fileName);
        topScores = getScoresFromFile(fileName);
        addToTopScores(score, topScores, maxNrOfTopScores);
        writeToFile(topScores, fileName);
    }

    /**
     * Create a file if it doesn't already exist.
     *
     * @author Irja Vuorela
     */
    private void createFile(String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a list where each line in the file is an element (each score in the file).
     * Return an empty list if the file doesn't exist.
     *
     * @param fileName name of the text file.
     * @return list of top scores
     * @author Irja Vuorela
     */
    public List<Integer> getScoresFromFile(String fileName) {
        ArrayList<Integer> scores = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                scores.add(Integer.parseInt(input.nextLine()));
            }
        } catch (FileNotFoundException e) {
            List<Integer> emptyList = new ArrayList<>();
            return emptyList;
        }
        return scores;
    }

    /**
     * Checks if top score, inserts it in the correct position among the top scores and trims the list if needed.
     *
     * @param score         the player's score.
     * @param topScores     the best scores.
     * @param nrOfTopScores
     * @author Irja Vuorela
     */
    private void addToTopScores(int score, List<Integer> topScores, int nrOfTopScores) {
        if (isTopScore(score, topScores)) {
            insertSorted(score, topScores);
            trimScoresList(topScores, nrOfTopScores);
        }
    }

    /**
     * Checks if a score is high enough to be a top score.
     *
     * @param score     The player's score.
     * @param topScores List of top scores.
     * @return true if score is a top score.
     * @author Irja Vuorela
     */
    public boolean isTopScore(int score, List<Integer> topScores) {

        // True if the list isn't filled yet.
        if (topScores.size() < maxNrOfTopScores) {
            return true;
        }
        // True if score is better than the worst top score
        else if (topScores.get(maxNrOfTopScores - 1) < score) {
            return true;
        } else return false;
    }

    /**
     * Inserts the score in the correct position among sorted scores.
     *
     * @param score  the player's score
     * @param scores the top scores (assumed sorted highest first).
     * @author Irja Vuorela
     */
    private void insertSorted(int score, List<Integer> scores) {
        if (!scores.isEmpty()) {
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) > score) {
                    continue;
                }
                int temp = scores.get(i);
                scores.set(i, score);
                score = temp;
            }
        }
        scores.add(score);
    }

    /**
     * Remove excess scores from list of top scores.
     *
     * @param scores        list of top scores
     * @param nrOfTopScores the total number of top scores allowed in the list
     * @Irja Vuorela
     */
    private void trimScoresList(List scores, int nrOfTopScores) {
        if (nrOfTopScores > 1) {
            while (scores.size() > nrOfTopScores) {
                scores.remove(scores.size() - 1);
            }
        }
    }

    /**
     * Write a list of scores to a text file with each score on a new line.
     *
     * @param scores   list of scores
     * @param fileName name of the file
     * @author Irja Vuorela
     */
    private void writeToFile(List<Integer> scores, String fileName) {
        try {
            writer = new FileWriter(fileName);
            for (Integer score : scores) {
                writer.write(score.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the number of top scores for the list of top scores.
     * @author Irja vuorela
     */
    public int getMaxNrOfTopScores() {
        return this.maxNrOfTopScores;
    }

    /**
     * Clears the text file from scores.
     *
     * @param fileName the name of the file
     */
    public void clearScores(String fileName) {
        List<Integer> emptyList = new ArrayList<>();
        writeToFile(emptyList, fileName);
    }

    /**
     * getter for name of the file for the saved top scores
     *
     * @return name of the high scores filename
     * @author Irja Vuorela
     */
    public String getFileName() {
        return this.fileName;
    }


}
