class Solution:
    def shortestDistance(
        self, maze: List[List[int]], start: List[int], destination: List[int]
    ) -> int:
        m, n = len(maze), len(maze[0])
        rs, cs = start
        rd, cd = destination
        dist = [[inf] * n for _ in range(m)]
        dist[rs][cs] = 0
        q = deque([(rs, cs)])
        while q:
            i, j = q.popleft()
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y, step = i, j, dist[i][j]
                while 0 <= x + a < m and 0 <= y + b < n and maze[x + a][y + b] == 0:
                    x, y, step = x + a, y + b, step + 1
                if step < dist[x][y]:
                    dist[x][y] = step
                    q.append((x, y))
        return -1 if dist[rd][cd] == inf else dist[rd][cd]
