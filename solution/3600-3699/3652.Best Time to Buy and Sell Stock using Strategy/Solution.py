class Solution:
    def maxProfit(self, prices: List[int], strategy: List[int], k: int) -> int:
        n = len(prices)
        s = [0] * (n + 1)
        t = [0] * (n + 1)
        for i, (a, b) in enumerate(zip(prices, strategy), 1):
            s[i] = s[i - 1] + a * b
            t[i] = t[i - 1] + a
        ans = s[n]
        for i in range(k, n + 1):
            ans = max(ans, s[n] - (s[i] - s[i - k]) + t[i] - t[i - k // 2])
        return ans
