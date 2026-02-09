class Solution:
    def dominantIndices(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        suf = nums[-1]
        for i in range(n - 2, -1, -1):
            if nums[i] > suf / (n - i - 1):
                ans += 1
            suf += nums[i]
        return ans
