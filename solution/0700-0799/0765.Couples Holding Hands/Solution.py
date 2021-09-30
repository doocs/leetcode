class Solution:
    def minSwapsCouples(self, row: List[int]) -> int:
        n = len(row) >> 1
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(0, len(row), 2):
            a, b = row[i] >> 1, row[i + 1] >> 1
            p[find(a)] = find(b)

        cnt = 0
        for i in range(n):
            if i == find(i):
                cnt += 1
        return n - cnt
