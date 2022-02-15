class Solution:
    def massage(self, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)
        if n < 2:
            return nums[0]
        a, b = nums[0], max(nums[0], nums[1])
        res = b
        for i in range(2, n):
            res = max(a + nums[i], b)
            a, b = b, res
        return res
