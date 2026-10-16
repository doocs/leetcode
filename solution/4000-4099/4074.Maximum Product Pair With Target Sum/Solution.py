from math import inf


class Solution:
    def maxProductPair(self, nums: list[int], target: int) -> list[int]:
        pos = {}
        mx = -inf
        ans = [-1, -1]
        for i, x in enumerate(nums):
            y = target - x
            if x != y and x * y > mx and y in pos:
                j = pos[y]
                mx = x * y
                ans = [i, j] if x > y else [j, i]
            pos[x] = i
        return ans
