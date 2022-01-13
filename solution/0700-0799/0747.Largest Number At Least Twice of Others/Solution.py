class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        mx = mid = 0
        ans = -1
        for i, v in enumerate(nums):
            if v > mx:
                mid, mx = mx, v
                ans = i
            elif v > mid:
                mid = v
        return ans if mx >= 2 * mid else -1
