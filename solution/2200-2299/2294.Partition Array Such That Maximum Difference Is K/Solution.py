class Solution:
    def partitionArray(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans, a = 1, nums[0]
        for b in nums:
            if b - a > k:
                a = b
                ans += 1
        return ans
