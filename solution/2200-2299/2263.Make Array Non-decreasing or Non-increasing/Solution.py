class Solution:
    def convertArray(self, nums: List[int]) -> int:
        def solve(nums):
            n = len(nums)
            f = [[0] * 1001 for _ in range(n + 1)]
            for i, x in enumerate(nums, 1):
                mi = inf
                for j in range(1001):
                    if mi > f[i - 1][j]:
                        mi = f[i - 1][j]
                    f[i][j] = mi + abs(x - j)
            return min(f[n])

        return min(solve(nums), solve(nums[::-1]))
