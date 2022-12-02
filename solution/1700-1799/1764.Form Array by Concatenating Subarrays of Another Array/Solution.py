class Solution:
    def canChoose(self, groups: List[List[int]], nums: List[int]) -> bool:
        n, m = len(groups), len(nums)
        i = j = 0
        while i < n and j < m:
            g = groups[i]
            if g == nums[j : j + len(g)]:
                j += len(g)
                i += 1
            else:
                j += 1
        return i == n
