class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        s = 0
        ans = [-1] * len(nums)
        for i, v in enumerate(nums):
            s += v
            if i >= k * 2:
                ans[i - k] = s // (k * 2 + 1)
                s -= nums[i - k * 2]
        return ans
