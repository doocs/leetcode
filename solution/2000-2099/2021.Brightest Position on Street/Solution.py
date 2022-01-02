class Solution:
    def brightestPosition(self, lights: List[List[int]]) -> int:
        d = defaultdict(int)
        for p, r in lights:
            d[p - r] += 1
            d[p + r + 1] -= 1
        s = mx = ans = 0
        for k in sorted(d):
            s += d[k]
            if s > mx:
                mx = s
                ans = k
        return ans
