class Solution:
    def trulyMostPopular(self, names: List[str], synonyms: List[str]) -> List[str]:
        def dfs(a):
            vis.add(a)
            mi, x = a, cnt[a]
            for b in g[a]:
                if b not in vis:
                    t, y = dfs(b)
                    if mi > t:
                        mi = t
                    x += y
            return mi, x

        g = defaultdict(list)
        for e in synonyms:
            a, b = e[1:-1].split(',')
            g[a].append(b)
            g[b].append(a)
        s = set()
        cnt = defaultdict(int)
        for x in names:
            name, freq = x[:-1].split("(")
            s.add(name)
            cnt[name] = int(freq)
        vis = set()
        ans = []
        for name in s:
            if name not in vis:
                name, freq = dfs(name)
                ans.append(f"{name}({freq})")
        return ans
