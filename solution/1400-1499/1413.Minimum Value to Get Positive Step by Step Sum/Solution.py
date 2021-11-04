class Solution:
    def minStartValue(self, nums: List[int]) -> int:
        n = len(nums)
        pre_sum = [nums[0]] * n
        for i in range(1, n):
            pre_sum[i] = pre_sum[i - 1] + nums[i]
        ans = maxsize
        for num in pre_sum:
            ans = min(ans, num)
        return ans * -1 + 1 if ans <= 0 else 1