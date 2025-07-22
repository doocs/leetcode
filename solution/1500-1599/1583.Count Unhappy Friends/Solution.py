class Solution:
    def unhappyFriends(
        self, n: int, preferences: List[List[int]], pairs: List[List[int]]
    ) -> int:
        d = [{x: j for j, x in enumerate(p)} for p in preferences]
        p = {}
        for x, y in pairs:
            p[x] = y
            p[y] = x
        ans = 0
        for x in range(n):
            y = p[x]
            for i in range(d[x][y]):
                u = preferences[x][i]
                v = p[u]
                if d[u][x] < d[u][v]:
                    ans += 1
                    break
        return ans
