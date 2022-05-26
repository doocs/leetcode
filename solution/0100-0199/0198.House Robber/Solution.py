class Solution:
    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)
        if n == 1:
            return nums[0]
        pre, cur = nums[0], max(nums[0], nums[1])
        for i in range(2, n):
            t = max(pre + nums[i], cur)
            pre, cur = cur, t
        return cur
