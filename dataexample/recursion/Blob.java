

class Color {
    int x_coordinate;
    int y_coordinate;
    static int[][] grid = new int[10][10]; // Example: Initialize with a 10x10 grid

    Color(int x, int y) {
        x_coordinate = x;
        y_coordinate = y;
        grid[x_coordinate][y_coordinate] = 1; // Mark the cell as part of a blob
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }
}

public class Blob {
    
    public static int count_cell(int x, int y) {
        // Check if the coordinates are out of bounds or if the cell is already visited
        if (x < 0 || x >= 10 || y < 0 || y >= 10 || Color.grid[x][y] == 0) {
            return 0; // Base case: if it's out of bounds or already visited, return 0
        }
        
        // Mark this cell as visited by setting it to 0
        Color.grid[x][y] = 0;

        // Recursively count all connected cells (up, down, left, right)
        return 1 + count_cell(x + 1, y) // right
                 + count_cell(x - 1, y) // left
                 + count_cell(x, y + 1) // down
                 + count_cell(x, y - 1); // up
    }

    public static void main(String[] args) {
        // Example of setting some cells in the grid
        new Color(1, 1);
        new Color(1, 2);
        new Color(2, 2);
        new Color(2, 1);

        // Start counting from a cell that's part of a blob
        int blobSize = count_cell(1, 1);
        System.out.println("Blob size: " + blobSize); // This should print the size of the blob
    }
}
