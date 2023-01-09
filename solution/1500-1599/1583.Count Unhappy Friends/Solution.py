class Solution:
    def unhappyFriends(
        self, n: int, preferences: List[List[int]], pairs: List[List[int]]
    ) -> int:
        d = [{p: i for i, p in enumerate(v)} for v in preferences]
        p = {}
        for x, y in pairs:
            p[x] = y
            p[y] = x
        ans = 0
        for x in range(n):
            y = p[x]
            ans += any(d[u][x] < d[u][p[u]] for u in preferences[x][: d[x][y]])
        return ans
