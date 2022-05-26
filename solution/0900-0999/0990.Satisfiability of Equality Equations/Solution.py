class Solution:
    def equationsPossible(self, equations: List[str]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(26))
        for e in equations:
            a, b = ord(e[0]) - ord('a'), ord(e[-1]) - ord('a')
            if e[1] == '=':
                p[find(a)] = find(b)
        for e in equations:
            a, b = ord(e[0]) - ord('a'), ord(e[-1]) - ord('a')
            if e[1] == '!' and find(a) == find(b):
                return False
        return True
