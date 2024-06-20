class Solution:
    def minMoves(self, rooks: List[List[int]]) -> int:
        rooks.sort()
        ans = sum(abs(x - i) for i, (x, _) in enumerate(rooks))
        rooks.sort(key=lambda x: x[1])
        ans += sum(abs(y - j) for j, (_, y) in enumerate(rooks))
        return ans
