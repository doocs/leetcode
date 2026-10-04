from math import lcm


class Solution:
    def subarrayLCM(self, nums: List[int], k: int) -> int:
        ans = 0
        for i in range(len(nums)):
            a = 1
            for b in nums[i:]:
                if k % b:
                    break
                a = lcm(a, b)
                ans += a == k
        return ans
