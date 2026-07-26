class Solution:
    def largestInteger(self, n: int, s: int) -> int:
        if n * 9 < s:
            return -1
        ans = 0
        for _ in range(n):
            x = min(s, 9)
            ans = ans * 10 + x
            s -= x
        return ans
