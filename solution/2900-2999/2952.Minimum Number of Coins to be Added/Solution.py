class Solution:
    def minimumAddedCoins(self, coins: list[int], target: int) -> int:
        coins.sort()
        s = 1
        ans = i = 0
        while s <= target:
            if i < len(coins) and coins[i] <= s:
                s += coins[i]
                i += 1
            else:
                s <<= 1
                ans += 1
        return ans
