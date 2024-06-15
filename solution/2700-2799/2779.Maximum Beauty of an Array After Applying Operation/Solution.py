class Solution:
    def maximumBeauty(self, nums: List[int], k: int) -> int:
        m = max(nums) + k * 2 + 2
        d = [0] * m
        for x in nums:
            d[x] += 1
            d[x + k * 2 + 1] -= 1
        return max(accumulate(d))
