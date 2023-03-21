class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = -1
        i, j = 0, len(nums) - 1
        while i < j:
            if (t := nums[i] + nums[j]) < k:
                ans = max(ans, t)
                i += 1
            else:
                j -= 1
        return ans
