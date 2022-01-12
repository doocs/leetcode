class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        dis = defaultdict(list)
        for a, b in dislikes:
            a, b = a - 1, b - 1
            dis[a].append(b)
            dis[b].append(a)

        for i in range(n):
            for j in dis[i]:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(dis[i][0])
        return True
