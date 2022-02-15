class Solution:
    def trulyMostPopular(self, names: List[str], synonyms: List[str]) -> List[str]:
        mp = defaultdict(int)
        p = defaultdict(str)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            if pa > pb:
                mp[pb] += mp[pa]
                p[pa] = pb
            else:
                mp[pa] += mp[pb]
                p[pb] = pa

        for e in names:
            idx = e.find("(")
            name, w = e[:idx], int(e[idx + 1 : -1])
            mp[name] = w
            p[name] = name
        for e in synonyms:
            idx = e.find(",")
            name1, name2 = e[1:idx], e[idx + 1 : -1]
            mp[name1] += 0
            mp[name2] += 0
            p[name1] = name1
            p[name2] = name2

        for e in synonyms:
            idx = e.find(",")
            name1, name2 = e[1:idx], e[idx + 1 : -1]
            union(name1, name2)
        return [f'{name}({mp[name]})' for name, w in mp.items() if name == find(name)]
