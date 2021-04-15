class Solution:
    def rob(self, nums: List[int]) -> int:
        def robRange(nums, start, end):
            if end - start == 0:
                return nums[start]
            pre, cur = 0, nums[start]
            for i in range(start + 1, end + 1):
                pre, cur = cur, max(pre + nums[i], cur)
            return cur
        if not nums:
            return 0
        return robRange(nums, 0, len(nums) - 1)
