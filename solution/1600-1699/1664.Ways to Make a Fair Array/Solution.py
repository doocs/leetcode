class Solution:
    def waysToMakeFair(self, nums: List[int]) -> int:
        x, y = sum(nums[1::2]), sum(nums[::2])
        ans = 0
        a = b = 0
        for i, v in enumerate(nums):
            if (i & 1) and x - v - a + b == y - b + a:
                ans += 1
            elif (i & 1) == 0 and y - v - b + a == x - a + b:
                ans += 1
            if i & 1:
                a += v
            else:
                b += v
        return ans
