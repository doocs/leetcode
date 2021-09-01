class Solution:
    def numSimilarGroups(self, strs: List[str]) -> int:
        n = len(strs)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def check(a, b):
            cnt = 0
            for i, c in enumerate(a):
                if c != b[i]:
                    cnt += 1
            return cnt <= 2

        for i in range(n):
            for j in range(i + 1, n):
                if check(strs[i], strs[j]):
                    p[find(i)] = find(j)

        return sum(i == find(i) for i in range(n))
