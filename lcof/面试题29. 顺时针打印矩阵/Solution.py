class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if len(matrix) == 0:
            return []

        s1, e1, s2, e2 = 0, 0, len(matrix) - 1, len(matrix[0]) - 1
        res = []
        while s1 <= s2 and e1 <= e2:
            res += self._spiral_add(matrix, s1, e1, s2, e2)
            s1, e1, s2, e2 = s1 + 1, e1 + 1, s2 - 1, e2 - 1
        return res

    def _spiral_add(self, matrix, s1, e1, s2, e2) -> List[int]:
        if s1 == s2:
            return [matrix[s1][j] for j in range(e1, e2 + 1)]
        if e1 == e2:
            return [matrix[i][e1] for i in range(s1, s2 + 1)]
        return [matrix[s1][j] for j in range(e1, e2)] + \
               [matrix[i][e2] for i in range(s1, s2)] + \
               [matrix[s2][j] for j in range(e2, e1, -1)] + \
               [matrix[i][e1] for i in range(s2, s1, -1)]
