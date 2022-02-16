class Solution:
    def checkWays(self, pairs: List[List[int]]) -> int:
        g = [[False] * 510 for _ in range(510)]
        v = defaultdict(list)
        for x, y in pairs:
            g[x][y] = g[y][x] = True
            v[x].append(y)
            v[y].append(x)
        nodes = []
        for i in range(510):
            if v[i]:
                nodes.append(i)
                g[i][i] = True
        nodes.sort(key=lambda x: len(v[x]))
        equal = False
        root = 0
        for i, x in enumerate(nodes):
            j = i + 1
            while j < len(nodes) and not g[x][nodes[j]]:
                j += 1
            if j < len(nodes):
                y = nodes[j]
                if len(v[x]) == len(v[y]):
                    equal = True
                for z in v[x]:
                    if not g[y][z]:
                        return 0
            else:
                root += 1
        if root > 1:
            return 0
        return 2 if equal else 1
