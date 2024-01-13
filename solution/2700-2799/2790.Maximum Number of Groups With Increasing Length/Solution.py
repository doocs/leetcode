class Solution:
    def maxIncreasingGroups(self, usageLimits: List[int]) -> int:
        usageLimits.sort()
        k, n = 0, len(usageLimits)
        for i in range(n):
            if usageLimits[i] > k:
                k += 1
                usageLimits[i] -= k
            if i + 1 < n:
                usageLimits[i + 1] += usageLimits[i]
        return k
