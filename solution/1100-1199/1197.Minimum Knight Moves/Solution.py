class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        q = deque([(0, 0)])
        ans = 0
        vis = set([(0, 0)])
        while q:
            for _ in range(len(q), 0, -1):
                i, j = q.popleft()
                if (i, j) == (x, y):
                    return ans
                for a, b in [
                    [-2, 1],
                    [-1, 2],
                    [1, 2],
                    [2, 1],
                    [2, -1],
                    [1, -2],
                    [-1, -2],
                    [-2, -1],
                ]:
                    c, d = i + a, j + b
                    if (c, d) not in vis:
                        vis.add((c, d))
                        q.append((c, d))
            ans += 1
        return -1
