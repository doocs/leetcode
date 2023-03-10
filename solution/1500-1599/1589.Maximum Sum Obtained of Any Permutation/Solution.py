class Solution:
    def maxSumRangeQuery(self, nums: List[int], requests: List[List[int]]) -> int:
        n = len(nums)
        d = [0] * n
        for l, r in requests:
            d[l] += 1
            if r + 1 < n:
                d[r + 1] -= 1
        for i in range(1, n):
            d[i] += d[i - 1]
        nums.sort()
        d.sort()
        mod = 10**9 + 7
        return sum(a * b for a, b in zip(nums, d)) % mod
