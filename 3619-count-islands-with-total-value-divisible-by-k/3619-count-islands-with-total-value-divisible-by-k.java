class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long islandSum = dfs(grid, visited, i, j, m, n);
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, boolean[][] visited, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }
        visited[r][c] = true;
        long sum = grid[r][c];
        sum += dfs(grid, visited, r + 1, c, m, n);
        sum += dfs(grid, visited, r - 1, c, m, n);
        sum += dfs(grid, visited, r, c + 1, m, n);
        sum += dfs(grid, visited, r, c - 1, m, n);
        return sum;
    }
}
