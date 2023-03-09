class Solution:
    def elementInNums(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        n, m = len(nums), len(queries)
        ans = [-1] * m
        for j, (t, i) in enumerate(queries):
            t %= 2 * n
            if t < n and i < n - t:
                ans[j] = nums[i + t]
            elif t > n and i < t - n:
                ans[j] = nums[i]
        return ans
