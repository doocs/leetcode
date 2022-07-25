class Solution:
    def numMovesStones(self, a: int, b: int, c: int) -> List[int]:
        a, b, c = sorted([a, b, c])
        ans = [0] * 2
        if c - a == 2:
            return ans
        if b - a < 3 or c - b < 3:
            ans[0] = 1
        else:
            ans[0] = 2
        ans[1] = c - a - 2
        return ans
