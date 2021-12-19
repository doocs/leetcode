class Solution:
    def getDescentPeriods(self, prices: List[int]) -> int:
        i, n = 0, len(prices)
        ans = 0
        while i < n:
            j = i
            while j + 1 < n and prices[j] - prices[j + 1] == 1:
                j += 1
            t = j - i + 1
            ans += t * (t + 1) // 2
            i = j + 1
        return ans
