class Solution:
    def subsets(self, nums: list[int]) -> list[list[int]]:
        res = []

        def dfs(i, n, t):
            res.append(t.copy())
            if i == n:
                return
            for j in range(i, n):
                t.append(nums[j])
                dfs(j + 1, n, t)
                t.pop()

        dfs(0, len(nums), [])
        return res
