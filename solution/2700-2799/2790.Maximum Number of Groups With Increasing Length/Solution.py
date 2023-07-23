class Solution:
    def maxIncreasingGroups(self, usageLimits: List[int]) -> int:
        usageLimits.sort()
        k = s = 0
        for x in usageLimits:
            s += x
            if s > k:
                k += 1
                s -= k
        return k
