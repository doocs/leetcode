class Solution:
    def subsets(self, nums: list[int]) -> list[list[int]]:
        ans = []
        for mask in range(1 << len(nums)):
            t = []
            for i, v in enumerate(nums):
                if (mask >> i) & 1:
                    t.append(v)
            ans.append(t)
        return ans
