class Solution:
    def maxBuilding(self, n: int, restrictions: List[List[int]]) -> int:
        r = restrictions
        r.append([1, 0])
        r.sort()
        if r[-1][0] != n:
            r.append([n, n - 1])
        m = len(r)
        for i in range(1, m):
            r[i][1] = min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0])
        for i in range(m - 2, 0, -1):
            r[i][1] = min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0])
        ans = 0
        for i in range(m - 1):
            t = (r[i][1] + r[i + 1][1] + r[i + 1][0] - r[i][0]) // 2
            ans = max(ans, t)
        return ans
