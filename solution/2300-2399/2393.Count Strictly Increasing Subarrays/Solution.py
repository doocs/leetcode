class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        ans = cnt = 1
        for x, y in pairwise(nums):
            if x < y:
                cnt += 1
            else:
                cnt = 1
            ans += cnt
        return ans
