class Solution:
    def widestPairOfIndices(self, nums1: list[int], nums2: list[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            s += a - b
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
