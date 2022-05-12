# """
# This is GridMaster's API interface.
# You should not implement it, or speculate about its implementation
# """
# class GridMaster(object):
#    def canMove(self, direction: str) -> bool:
#
#
#    def move(self, direction: str) -> bool:
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
            for dir, ndir, a, b in dirs:
                x, y = i + a, j + b
                if master.canMove(dir) and (x, y) not in s:
                    s.add((x, y))
                    master.move(dir)
                    dfs(x, y)
                    master.move(ndir)

        target = None
        s = set()
        dirs = [
            ['U', 'D', -1, 0],
            ['D', 'U', 1, 0],
            ['L', 'R', 0, -1],
            ['R', 'L', 0, 1],
        ]
        dfs(0, 0)
        if target is None:
            return -1
        s.remove((0, 0))
        q = deque([(0, 0)])
        ans = -1
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                if (i, j) == target:
                    return ans
                for _, _, a, b in dirs:
                    x, y = i + a, j + b
                    if (x, y) in s:
                        s.remove((x, y))
                        q.append((x, y))
        return -1
