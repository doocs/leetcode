class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        l = r = -1
        while r < len(nums) - 1:
            r += 1
            if nums[r] == 0:
                k -= 1
            if k < 0:
                l += 1
                if nums[l] == 0:
                    k += 1
        return r - l
