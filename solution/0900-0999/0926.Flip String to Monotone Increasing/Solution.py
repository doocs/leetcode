class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        left, right = [0] * (n + 1), [0] * (n + 1)
        ans = 0x3F3F3F3F
        for i in range(1, n + 1):
            left[i] = left[i - 1] + (1 if s[i - 1] == '1' else 0)
        for i in range(n - 1, -1, -1):
            right[i] = right[i + 1] + (1 if s[i] == '0' else 0)
        for i in range(0, n + 1):
            ans = min(ans, left[i] + right[i])
        return ans
