class Solution:
    def maxScore(self, prices: list[int]) -> int:
        cnt = Counter()
        for i, x in enumerate(prices):
            cnt[x - i] += x
        return max(cnt.values())
