class Solution:
    def hasPath(self, maze: List[List[int]], start: List[int], destination: List[int]) -> bool:
        def dfs(maze, start, destination):
            if visited[start[0]][start[1]]:
                return False
            if start[0] == destination[0] and start[1] == destination[1]:
                return True
            visited[start[0]][start[1]] = True
            l, r, u, d = start[1] - 1, start[1] + 1, start[0] - 1, start[0] + 1
            while l >= 0 and maze[start[0]][l] == 0:
                l -= 1
            if dfs(maze, [start[0], l + 1], destination):
                return True
            while r < len(maze[0]) and maze[start[0]][r] == 0:
                r += 1
            if dfs(maze, [start[0], r - 1], destination):
                return True
            while u >= 0 and maze[u][start[1]] == 0:
                u -= 1
            if dfs(maze, [u + 1, start[1]], destination):
                return True
            while d < len(maze) and maze[d][start[1]] == 0:
                d += 1
            if dfs(maze, [d - 1, start[1]], destination):
                return True
            return False

        visited = [[False for _ in maze[0]] for _ in maze]
        return dfs(maze, start, destination)
