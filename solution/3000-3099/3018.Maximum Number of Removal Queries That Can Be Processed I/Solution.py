class Solution:
    def maximumProcessableQueries(self, nums: List[int], queries: List[int]) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        m = len(queries)
        for i in range(n):
            for j in range(n - 1, i - 1, -1):
                if i:
                    f[i][j] = max(
                        f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]])
                    )
                if j + 1 < n:
                    f[i][j] = max(
                        f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]])
                    )
                if f[i][j] == m:
                    return m
        return max(f[i][i] + (nums[i] >= queries[f[i][i]]) for i in range(n))
