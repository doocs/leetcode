class Solution:
    def smallestEquivalentString(self, s1: str, s2: str, baseStr: str) -> str:
        p = list(range(26))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(len(s1)):
            a, b = ord(s1[i]) - ord('a'), ord(s2[i]) - ord('a')
            pa, pb = find(a), find(b)
            if pa < pb:
                p[pb] = pa
            else:
                p[pa] = pb

        res = []
        for a in baseStr:
            a = ord(a) - ord('a')
            res.append(chr(find(a) + ord('a')))
        return ''.join(res)
