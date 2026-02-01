class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        def lis(arr: List[int]) -> int:
            g = []
            for x in arr:
                j = bisect_left(g, x)
                if j == len(g):
                    g.append(x)
                else:
                    g[j] = x
            return len(g)

        ans = 0
        m = max(nums).bit_length()
        for i in range(m):
            arr = [x for x in nums if x >> i & 1]
            ans = max(ans, lis(arr))
        return ans
