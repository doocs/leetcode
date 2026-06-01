class Solution:
    def digitFrequencyScore(self, n: int) -> int:
        ans = 0
        while n:
            n, x = divmod(n, 10)
            ans += x
        return ans
