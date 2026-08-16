class Solution:
    def minOperations(self, s: str) -> int:
        n = len(s)
        ans = inf
        for k in range(n):
            t = k
            i, j = 0, n - 1
            while i < j:
                x = ord(s[(i + k) % n]) - ord('a')
                y = ord(s[(j + k) % n]) - ord('a')
                d = abs(x - y)
                t += min(d, 26 - d)
                i, j = i + 1, j - 1
            ans = min(ans, t)
        return ans
