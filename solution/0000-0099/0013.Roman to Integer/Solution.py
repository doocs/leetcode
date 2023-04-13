class Solution:
    def romanToInt(self, s: str) -> int:
        ans = 0
        d = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
        for a, b in pairwise(s):
            if d[a] < d[b]:
                ans -= d[a]
            else:
                ans += d[a]
        ans += d[s[-1]]
        return ans
