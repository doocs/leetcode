# """
# This is GridMaster's API interface.
# You should not implement it, or speculate about its implementation
# """
# class GridMaster(object):
#    def canMove(self, direction: str) -> bool:
#
#
#    def move(self, direction: str) -> int:
#
#
#    def isTarget(self) -> None:
#
#


class Solution(object):
    def findShortestPath(self, master: 'GridMaster') -> int:
        def dfs(i, j):
            nonlocal target
            if master.isTarget():
                target = (i, j)
            for dir, (a, b, ndir) in dirs.items():
                x, y = i + a, j + b
                if 0 <= x < N and 0 <= y < N and master.canMove(dir) and g[x][y] == -1:
                    g[x][y] = master.move(dir)
                    dfs(x, y)
                    master.move(ndir)

        target = (-1, -1)
        N = 200
        INF = 0x3F3F3F3F
        g = [[-1] * N for _ in range(N)]
        dirs = {
            'U': (-1, 0, 'D'),
            'D': (1, 0, 'U'),
            'L': (0, -1, 'R'),
            'R': (0, 1, 'L'),
        }
        dfs(100, 100)
        if target == (-1, -1):
            return -1
        q = [(0, 100, 100)]
        dist = [[INF] * N for _ in range(N)]
        dist[100][100] = 0
        while q:
            w, i, j = heappop(q)
            if (i, j) == target:
                return w
            for a, b, _ in dirs.values():
                x, y = i + a, j + b
                if (
                    0 <= x < N
                    and 0 <= y < N
                    and g[x][y] != -1
                    and dist[x][y] > w + g[x][y]
                ):
                    dist[x][y] = w + g[x][y]
                    heappush(q, (dist[x][y], x, y))
        return 0
