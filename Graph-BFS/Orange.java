/**
994. Rotting Oranges
腐烂的橙子：一个腐烂的橙子周围4个临近的橙子都会腐烂（而且是同时）
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Algrithom：BFS
真理：逐层讨论
将当前本层于下一层的“关系”存下来 （一般用hashmap， 通常情况下每一层逐渐变大，所以一个key对应一个list of value）
将当前本层的所有“qulified” 的object存下来（一般用queue）


*/

class Solution {
    public int orangesRotting(int[][] grid) {

        Queue<Cell> queue = new LinkedList<Cell>();
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Cell(i, j));
                }
            }
        }
        
        int count = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            System.out.println(queue);
            for (int i = 0; i < size; i++) {
                Cell current = queue.poll();
                List<Cell> adj_list = this.get_adj(current);
                for (Cell adj: adj_list) {
                    int current_i = adj.get_i();
                    int current_j = adj.get_j();
                    if (current_i >= 0 && current_i < grid.length && current_j >= 0 && current_j < grid[0].length && grid[current_i][current_j] == 1) {
                        grid[current_i][current_j] = 2;
                        queue.offer(adj);
                    }
                }
            }
        }
        

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return count == -1? 0:count;
    }


    private List<Cell> get_adj(Cell c) {
        int i = c.get_i();
        int j = c.get_j();
        List<Cell> adj_cells = new ArrayList<>();
        adj_cells.add(new Cell(i+1, j));
        adj_cells.add(new Cell(i, j+1));
        adj_cells.add(new Cell(i-1, j));
        adj_cells.add(new Cell(i, j-1));
        return adj_cells;
    }

    class Cell {
        int i;
        int j;
        Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int get_i() {
            return i;
        }
        public int get_j() {
            return j;
        }
    }
}