class Solution:
    def getMaximumConsecutive(self, coins: list[int]) -> int:
        ans = 1
        for v in sorted(coins):
            if v > ans:
                break
            ans += v
        return ans
