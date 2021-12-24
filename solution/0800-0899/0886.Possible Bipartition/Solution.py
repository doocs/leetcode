class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        mp = defaultdict(list)
        for i, j in dislikes:
            mp[i - 1].append(j - 1)
            mp[j - 1].append(i - 1)
        for i in range(n):
            dis = mp[i]
            for j in dis:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(dis[0])
        return True
