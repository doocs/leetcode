class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        p = list(range(n))
        
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]
        
        for a, b in edges:
            if find(a) == find(b):
                return False
            p[find(a)] = find(b)
            n -= 1
        return n == 1
