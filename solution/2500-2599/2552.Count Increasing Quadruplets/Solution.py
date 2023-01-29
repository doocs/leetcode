class Solution:
    def countQuadruplets(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        for j in range(1, n - 2):
            cnt = sum(nums[l] > nums[j] for l in range(j + 1, n))
            for k in range(j + 1, n - 1):
                if nums[j] > nums[k]:
                    f[j][k] = cnt
                else:
                    cnt -= 1
        for k in range(2, n - 1):
            cnt = sum(nums[i] < nums[k] for i in range(k))
            for j in range(k - 1, 0, -1):
                if nums[j] > nums[k]:
                    g[j][k] = cnt
                else:
                    cnt -= 1
        return sum(
            f[j][k] * g[j][k] for j in range(1, n - 2) for k in range(j + 1, n - 1)
        )
