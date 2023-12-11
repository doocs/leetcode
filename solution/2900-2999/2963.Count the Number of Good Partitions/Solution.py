class Solution:
    def numberOfGoodPartitions(self, nums: List[int]) -> int:
        last = {x: i for i, x in enumerate(nums)}
        mod = 10**9 + 7
        j, k = -1, 0
        for i, x in enumerate(nums):
            j = max(j, last[x])
            k += i == j
        return pow(2, k - 1, mod)
