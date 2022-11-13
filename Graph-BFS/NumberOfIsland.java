/**
200. Number of Islands

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Algrithom:
1. Triverse the whole matrix, find "1", go into bfs
2. Using bfs, going vertically and horizentally, if touch the boundary (i and j larger than the length or smaller than 0, or grid[i][j] == 0)
    Note that here we also defin boudnary when there is a "water" -> "0"
    将所有能通过直着走走到的地方换成0， 当遇到原本就是0的地方停下，一整个island全部变成water，再往下走如果还有“1”证明这是一个新的island


*/


class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j <grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count += bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public int bfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return 0;
        }
        grid[i][j] = '0';
        bfs(grid, i+1, j);
        bfs(grid, i-1, j);
        bfs(grid, i, j+1);
        bfs(grid, i, j-1);
        return 1;
    }
}

// 也可以先count再modify “1” to “0”
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j <grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count += 1;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void bfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        bfs(grid, i+1, j);
        bfs(grid, i-1, j);
        bfs(grid, i, j+1);
        bfs(grid, i, j-1);
    }
}
}