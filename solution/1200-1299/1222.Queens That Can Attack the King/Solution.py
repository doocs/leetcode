class Solution:
    def queensAttacktheKing(
        self, queens: List[List[int]], king: List[int]
    ) -> List[List[int]]:
        n = 8
        s = {(i, j) for i, j in queens}
        ans = []
        for a, b in [
            [-1, 0],
            [1, 0],
            [0, -1],
            [0, 1],
            [1, 1],
            [1, -1],
            [-1, 1],
            [-1, -1],
        ]:
            x, y = king
            while 0 <= x + a < n and 0 <= y + b < n:
                x, y = x + a, y + b
                if (x, y) in s:
                    ans.append([x, y])
                    break
        return ans
