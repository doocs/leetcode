class Solution:
    def minimumCost(self, nums: list[int], k: int) -> int:
        cnt = 0
        cur = k
        mod = 10**9 + 7
        for x in nums:
            diff = x - cur
            if diff > 0:
                m = (diff + k - 1) // k
                cur += m * k
                cnt += m
            cur -= x
        cnt %= mod
        return (1 + cnt) * cnt // 2 % mod
