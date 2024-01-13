class Solution:
    def conveyorBelt(self, matrix: List[str], start: List[int], end: List[int]) -> int:
        dirs = (-1, 0, 1, 0, -1)
        d = {"^": 0, "v": 2, "<": 3, ">": 1}
        i, j = start
        q = deque([(i, j)])
        m, n = len(matrix), len(matrix[0])
        dist = [[inf] * n for _ in range(m)]
        dist[i][j] = 0
        while 1:
            i, j = q.popleft()
            if i == end[0] and j == end[1]:
                return int(dist[i][j])
            for k in range(4):
                x, y = i + dirs[k], j + dirs[k + 1]
                t = dist[i][j] + int(k != d[matrix[i][j]])
                if 0 <= x < m and 0 <= y < n and t < dist[x][y]:
                    dist[x][y] = t
                    if dist[x][y] == dist[i][j]:
                        q.appendleft((x, y))
                    else:
                        q.append((x, y))
