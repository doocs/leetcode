class Solution:
    def widestPairOfIndices(self, nums1: List[int], nums2: List[int]) -> int:
        d = {0: -1}
        ans = s = 0
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            s += a - b
            if s in d:
                ans = max(ans, i - d[s])
            else:
                d[s] = i
        return ans
