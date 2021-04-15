class Solution:
    def rob(self, nums: List[int]) -> int:
        def robRange(nums, start, end):
            if end - start == 0:
                return nums[start]
            pre, cur = 0, nums[start]
            for i in range(start + 1, end + 1):
                pre, cur = cur, max(pre + nums[i], cur)
            return cur
        n = len(nums)
        if n == 1:
            return nums[0]
        s1, s2 = robRange(nums, 0, n - 2), robRange(nums, 1, n - 1)
        return max(s1, s2)
