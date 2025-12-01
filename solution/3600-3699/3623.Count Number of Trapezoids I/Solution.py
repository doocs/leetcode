class Solution:
    def countTrapezoids(self, points: List[List[int]]) -> int:
        mod = 10**9 + 7
        cnt = Counter(p[1] for p in points)
        ans = s = 0
        for v in cnt.values():
            t = v * (v - 1) // 2
            ans = (ans + s * t) % mod
            s += t
        return ans
