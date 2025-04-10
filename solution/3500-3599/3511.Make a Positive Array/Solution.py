class Solution:
    def makeArrayPositive(self, nums: List[int]) -> int:
        l = -1
        ans = pre_mx = s = 0
        for r, x in enumerate(nums):
            s += x
            if r - l > 2 and s <= pre_mx:
                ans += 1
                l = r
                pre_mx = s = 0
            elif r - l >= 2:
                pre_mx = max(pre_mx, s - x - nums[r - 1])
        return ans
