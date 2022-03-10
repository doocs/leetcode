class Solution:
    def maxSideLength(self, mat: List[List[int]], threshold: int) -> int:
        m, n = len(mat), len(mat[0])
        s = [[0] * 310 for _ in range(310)]
        for i in range(m):
            for j in range(n):
                s[i + 1][j + 1] = s[i][j + 1] + s[i + 1][j] - s[i][j] + mat[i][j]

        def check(l):
            for i in range(m):
                for j in range(n):
                    if i + l - 1 < m and j + l - 1 < n:
                        i1, j1 = i + l - 1, j + l - 1
                        t = s[i1 + 1][j1 + 1] - s[i1 + 1][j] - s[i][j1 + 1] + s[i][j]
                        if t <= threshold:
                            return True
            return False

        left, right = 0, min(m, n)
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
