class Solution:
    def numSubmat(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if mat[i][j]:
                    g[i][j] = 1 if j == 0 else 1 + g[i][j - 1]
        ans = 0
        for j in range(n):
            stk = []
            for i in range(m):
                cur = g[i][j]
                while stk and stk[-1][0] >= cur:
                    stk.pop()
                cnt = cur * (i + 1)
                if stk:
                    cnt = stk[-1][2] + cur * (i - stk[-1][1])
                ans += cnt
                stk.append((cur, i, cnt))
        return ans
