class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        pre_sum = list(accumulate(stones))
        f = pre_sum[len(stones) - 1]
        for i in range(len(stones) - 2, 0, -1):
            f = max(f, pre_sum[i] - f)
        return f
