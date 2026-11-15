class Solution:
    def countConsistent(self, nums: list[int]) -> int:
        n = len(nums)
        ans = 0
        for i, x in enumerate(nums):
            y = x
            for j in range(i, n):
                x &= nums[j]
                y = min(y, nums[j])
                if x == y:
                    ans += 1
        return ans
