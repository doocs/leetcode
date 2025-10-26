class Solution:
    def maxCaloriesBurnt(self, heights: list[int]) -> int:
        heights.sort()
        pre = 0
        l, r = 0, len(heights) - 1
        ans = 0
        while l < r:
            ans += (heights[r] - pre) ** 2
            ans += (heights[l] - heights[r]) ** 2
            pre = heights[l]
            l, r = l + 1, r - 1
        ans += (heights[r] - pre) ** 2
        return ans
