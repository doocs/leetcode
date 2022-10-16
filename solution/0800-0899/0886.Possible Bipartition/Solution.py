class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        g = defaultdict(list)
        for a, b in dislikes:
            a, b = a - 1, b - 1
            g[a].append(b)
            g[b].append(a)
        p = list(range(n))
        for i in range(n):
            for j in g[i]:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(g[i][0])
        return True
