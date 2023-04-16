class Solution:
    def findPrefixScore(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        mx = 0
        for i, x in enumerate(nums):
            mx = max(mx, x)
            ans[i] = x + mx + (0 if i == 0 else ans[i - 1])
        return ans
