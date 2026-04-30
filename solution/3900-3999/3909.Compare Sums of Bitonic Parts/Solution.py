class Solution:
    def compareBitonicSums(self, nums: list[int]) -> int:
        l, r = nums[0], sum(nums)
        for a, b in pairwise(nums):
            if a > b:
                break
            l += b
            r -= a
        if l == r:
            return -1
        return 0 if l > r else 1
