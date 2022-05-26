class Solution {
    private boolean[][] visited;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        visited = new boolean[m][n];
        return dfs(maze, start, destination);
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination) {
        if (visited[start[0]][start[1]]) return false;
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        visited[start[0]][start[1]] = true;

        int l = start[1] - 1, r = start[1] + 1, u = start[0] - 1, d = start[0] + 1;

        while (l >= 0 && maze[start[0]][l] == 0) --l;
        if (dfs(maze, new int[]{start[0], l + 1}, destination)) return true;

        while (r < maze[0].length && maze[start[0]][r] == 0) ++r;
        if (dfs(maze, new int[]{start[0], r - 1}, destination)) return true;

        while (u >= 0 && maze[u][start[1]] == 0) --u;
        if (dfs(maze, new int[]{u + 1, start[1]}, destination)) return true;

        while (d < maze.length && maze[d][start[1]] == 0) ++d;
        if (dfs(maze, new int[]{d - 1, start[1]}, destination)) return true;

        return false;
    }
}