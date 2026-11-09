class Solution:
    def multiply(self, mat1: list[list[int]], mat2: list[list[int]]) -> list[list[int]]:
        def f(mat: list[list[int]]) -> list[list[int]]:
            g = [[] for _ in range(len(mat))]
            for i, row in enumerate(mat):
                for j, x in enumerate(row):
                    if x:
                        g[i].append((j, x))
            return g

        g1 = f(mat1)
        g2 = f(mat2)
        m, n = len(mat1), len(mat2[0])
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for k, x in g1[i]:
                for j, y in g2[k]:
                    ans[i][j] += x * y
        return ans
