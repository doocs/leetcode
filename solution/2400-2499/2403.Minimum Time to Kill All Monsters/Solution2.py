class Solution:
    def minimumTime(self, power: List[int]) -> int:
        n = len(power)
        dp = [inf] * (1 << n)
        dp[0] = 0
        for mask in range(1, 1 << n):
            cnt = mask.bit_count()
            for i, v in enumerate(power):
                if (mask >> i) & 1:
                    dp[mask] = min(dp[mask], dp[mask ^ (1 << i)] + (v + cnt - 1) // cnt)
        return dp[-1]
