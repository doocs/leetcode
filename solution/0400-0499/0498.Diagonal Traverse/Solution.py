class Solution:
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        m, n = len(mat), len(mat[0])
        ans, t = [], []
        for i in range(m + n):
            r = 0 if i < n else i - n + 1
            c = i if i < n else n - 1
            while r < m and c >= 0:
                t.append(mat[r][c])
                r += 1
                c -= 1
            if i % 2 == 0:
                t.reverse()
            ans.extend(t)
            t.clear()
        return ans
