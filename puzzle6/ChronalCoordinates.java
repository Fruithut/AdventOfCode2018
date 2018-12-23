import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChronalCoordinates {
    
    static class Coordinate {
        int x, y, id;
        Coordinate(int id, String input) {
            String[] point = input.split(", ");
            this.id = id;
            this.x = Integer.parseInt(point[0]);
            this.y = Integer.parseInt(point[1]);
        }
    }
    
    static List<Coordinate> getCoordinateInput() {
        List<Coordinate> coordinateList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));) {
            String input;
            int idCount = 0;
            while ((input = reader.readLine()) != null) {
                coordinateList.add(new Coordinate(idCount++, input));
            }
        } catch (IOException e) {
            e.printStackTrace();
            for (Throwable t : e.getSuppressed()) t.printStackTrace();
        }
        return coordinateList;
    }

    //Part 1 solution
    static void findMaximumField(int matrixSize, List<Coordinate> coordinates) {
        //Assumes matrix is large enough to contain all coordinates in input
        int[][] matrix = new int[matrixSize][matrixSize];
        int matrixWidth = matrix.length, matrixHeight = matrix[0].length;

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixHeight; j++) {
                int shortestDistance = Integer.MAX_VALUE;

                for (Coordinate co : coordinates) {
                    int currentDistance = Math.abs(co.x - i) + Math.abs(co.y - j);
                    //Equal distance is border between two coordinate fields
                    if (currentDistance == shortestDistance) matrix[i][j] = -1;
                    else if (currentDistance < shortestDistance) {
                        shortestDistance = currentDistance;
                        matrix[i][j] = co.id;
                    }
                }

            }
        }

        Set<Integer> edgeMembers = new HashSet<>();
        for (int i = 0; i < matrixWidth; i++) {
            edgeMembers.add(matrix[i][matrixWidth - 1]);
            edgeMembers.add(matrix[matrixWidth - 1][i]);
            edgeMembers.add(matrix[i][0]);
            edgeMembers.add(matrix[0][i]);
        }

        int[] sizeCount = new int[coordinates.size()];
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixHeight; j++) {
                if (edgeMembers.contains(matrix[i][j])) continue;
                sizeCount[matrix[i][j]] += 1;
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int x : sizeCount) if (x > max) max = x;
        System.out.print(max);
    }
    
    public static void main(String[] args) {
        findMaximumField(400, getCoordinateInput());
    }
}