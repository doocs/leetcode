class Solution:
    def paintingPlan(self, n: int, k: int) -> int:
        if k == n * n:
            return 1
        ans = 0
        for i in range(n + 1):
            for j in range(n + 1):
                if n * (i + j) - i * j == k:
                    ans += comb(n, i) * comb(n, j)
        return ans
