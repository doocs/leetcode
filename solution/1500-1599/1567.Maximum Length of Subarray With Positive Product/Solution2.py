class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        n = len(nums)
        f = int(nums[0] > 0)
        g = int(nums[0] < 0)
        ans = f
        for i in range(1, n):
            ff = gg = 0
            if nums[i] > 0:
                ff = f + 1
                gg = 0 if g == 0 else g + 1
            elif nums[i] < 0:
                ff = 0 if g == 0 else g + 1
                gg = f + 1
            f, g = ff, gg
            ans = max(ans, f)
        return ans
