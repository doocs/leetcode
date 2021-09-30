class Solution:
    def earliestAcq(self, logs: List[List[int]], n: int) -> int:
        p = list(range(n))
        logs.sort(key=lambda x: x[0])

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for t, a, b in logs:
            pa, pb = find(a), find(b)
            if pa == pb:
                continue
            p[pa] = pb
            n -= 1
            if n == 1:
                return t
        return -1
