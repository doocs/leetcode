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
#    def isTarget(self) -> bool:
#
#


class Solution(object):
    def findShortestPath(self, master: "GridMaster") -> int:
        def dfs(x: int, y: int) -> None:
            nonlocal target
            if master.isTarget():
                target = (x, y)
            for k in range(4):
                dx, dy = dirs[k], dirs[k + 1]
                nx, ny = x + dx, y + dy
                if (
                    0 <= nx < m
                    and 0 <= ny < n
                    and g[nx][ny] == -1
                    and master.canMove(s[k])
                ):
                    g[nx][ny] = master.move(s[k])
                    dfs(nx, ny)
                    master.move(s[(k + 2) % 4])

        dirs = (-1, 0, 1, 0, -1)
        s = "URDL"
        m = n = 200
        g = [[-1] * n for _ in range(m)]
        target = (-1, -1)
        sx = sy = 100
        dfs(sx, sy)
        if target == (-1, -1):
            return -1
        pq = [(0, sx, sy)]
        dist = [[inf] * n for _ in range(m)]
        dist[sx][sy] = 0
        while pq:
            w, x, y = heappop(pq)
            if (x, y) == target:
                return w
            for dx, dy in pairwise(dirs):
                nx, ny = x + dx, y + dy
                if (
                    0 <= nx < m
                    and 0 <= ny < n
                    and g[nx][ny] != -1
                    and w + g[nx][ny] < dist[nx][ny]
                ):
                    dist[nx][ny] = w + g[nx][ny]
                    heappush(pq, (dist[nx][ny], nx, ny))
        return -1
