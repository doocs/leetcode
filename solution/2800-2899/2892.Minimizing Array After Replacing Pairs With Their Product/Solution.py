class Solution:
    def minArrayLength(self, nums: List[int], k: int) -> int:
        ans, y = 1, nums[0]
        for x in nums[1:]:
            if x == 0:
                return 1
            if x * y <= k:
                y *= x
            else:
                y = x
                ans += 1
        return ans
