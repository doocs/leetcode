class Solution:
    def matrixBlockSum(self, mat: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        pre = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                pre[i][j] = (
                    pre[i - 1][j]
                    + pre[i][j - 1]
                    - pre[i - 1][j - 1]
                    + mat[i - 1][j - 1]
                )

        def get(i, j):
            i = max(min(m, i), 0)
            j = max(min(n, j), 0)
            return pre[i][j]

        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                ans[i][j] = (
                    get(i + k + 1, j + k + 1)
                    - get(i + k + 1, j - k)
                    - get(i - k, j + k + 1)
                    + get(i - k, j - k)
                )
        return ans
