class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        p = list(range(1010))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in edges:
            if find(a) == find(b):
                return [a, b]
            p[find(a)] = find(b)
        return []
