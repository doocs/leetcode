class Solution:
    def maximumScore(self, nums: List[int]) -> int:
        n = len(nums)
        suf = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            suf[i] = min(nums[i], suf[i + 1])
        ans = -inf
        pre = 0
        for i in range(n - 1):
            pre += nums[i]
            ans = max(ans, pre - suf[i + 1])
        return ans
