class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        mx = max(nums)
        ans = cnt = 0
        for v in nums:
            if v == mx:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 0
        return ans
