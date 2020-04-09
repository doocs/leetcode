class Solution:
    def maxChunksToSorted(self, arr):
        """
        :type arr: List[int]
        :rtype: int
        """
        ans = 0
        loc = 0
        for i, val in enumerate(arr):
            loc = val if loc < val else loc
            if loc == i:
                ans += 1
        return ans
