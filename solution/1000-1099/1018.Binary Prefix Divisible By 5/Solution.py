class Solution:
    def prefixesDivBy5(self, nums: list[int]) -> list[bool]:
        ans = []
        x = 0
        for v in nums:
            x = (x << 1 | v) % 5
            ans.append(x == 0)
        return ans
