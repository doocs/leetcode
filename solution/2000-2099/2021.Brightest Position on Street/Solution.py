class Solution:
    def brightestPosition(self, lights: List[List[int]]) -> int:
        d = defaultdict(int)
        for i, j in lights:
            l, r = i - j, i + j
            d[l] += 1
            d[r + 1] -= 1
        ans = s = mx = 0
        for k in sorted(d):
            s += d[k]
            if mx < s:
                mx = s
                ans = k
        return ans
