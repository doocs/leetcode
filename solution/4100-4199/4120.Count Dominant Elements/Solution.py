class Solution:
    def countDominantElements(self, nums: list[int]) -> int:
        ans = 0
        for i, x in enumerate(nums):
            s = cnt = 0
            if i > 0:
                s += nums[i - 1]
                cnt += 1
            if i < len(nums) - 1:
                s += nums[i + 1]
                cnt += 1
            if cnt == 0 or x >= s / cnt:
                ans += 1
        return ans
