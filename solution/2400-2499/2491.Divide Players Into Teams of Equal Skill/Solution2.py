class Solution:
    def dividePlayers(self, skill: List[int]) -> int:
        s = sum(skill)
        m = len(skill) >> 1
        if s % m:
            return -1
        t = s // m
        d = defaultdict(int)
        ans = 0
        for v in skill:
            if d[t - v]:
                ans += v * (t - v)
                m -= 1
                d[t - v] -= 1
            else:
                d[v] += 1
        return -1 if m else ans
