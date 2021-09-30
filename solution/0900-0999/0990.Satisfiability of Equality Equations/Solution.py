class Solution:
    def equationsPossible(self, equations: List[str]) -> bool:
        p = [i for i in range(26)]

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for e in equations:
            a, r, b = ord(e[0]) - ord('a'), e[1:3], ord(e[3]) - ord('a')
            if r == '==':
                p[find(a)] = find(b)
        for e in equations:
            a, r, b = ord(e[0]) - ord('a'), e[1:3], ord(e[3]) - ord('a')
            if r == '!=' and find(a) == find(b):
                return False
        return True
