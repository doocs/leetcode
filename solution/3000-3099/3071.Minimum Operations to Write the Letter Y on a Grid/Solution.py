class Solution:
    def minimumOperationsToWriteY(self, grid: List[List[int]]) -> int:
        n = len(grid)
        cnt1 = Counter()
        cnt2 = Counter()
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                a = i == j and i <= n // 2
                b = i + j == n - 1 and i <= n // 2
                c = j == n // 2 and i >= n // 2
                if a or b or c:
                    cnt1[x] += 1
                else:
                    cnt2[x] += 1
        return min(
            n * n - cnt1[i] - cnt2[j] for i in range(3) for j in range(3) if i != j
        )
