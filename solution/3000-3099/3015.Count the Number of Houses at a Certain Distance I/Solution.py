class Solution:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        x, y = x - 1, y - 1
        ans = [0] * n
        for i in range(n):
            for j in range(i + 1, n):
                a = j - i
                b = abs(i - x) + 1 + abs(j - y)
                c = abs(i - y) + 1 + abs(j - x)
                ans[min(a, b, c) - 1] += 2
        return ans
