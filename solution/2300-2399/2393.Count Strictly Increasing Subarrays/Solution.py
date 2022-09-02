class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        ans = i = 0
        while i < len(nums):
            j = i + 1
            while j < len(nums) and nums[j] > nums[j - 1]:
                j += 1
            cnt = j - i
            ans += (1 + cnt) * cnt // 2
            i = j
        return ans
