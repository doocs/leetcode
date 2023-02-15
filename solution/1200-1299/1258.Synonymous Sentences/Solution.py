class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa != pb:
            if self.size[pa] > self.size[pb]:
                self.p[pb] = pa
                self.size[pa] += self.size[pb]
            else:
                self.p[pa] = pb
                self.size[pb] += self.size[pa]


class Solution:
    def generateSentences(self, synonyms: List[List[str]], text: str) -> List[str]:
        def dfs(i):
            if i >= len(sentence):
                ans.append(' '.join(t))
                return
            if sentence[i] not in d:
                t.append(sentence[i])
                dfs(i + 1)
                t.pop()
            else:
                root = uf.find(d[sentence[i]])
                for j in g[root]:
                    t.append(words[j])
                    dfs(i + 1)
                    t.pop()

        words = list(set(chain.from_iterable(synonyms)))
        d = {w: i for i, w in enumerate(words)}
        uf = UnionFind(len(d))
        for a, b in synonyms:
            uf.union(d[a], d[b])
        g = defaultdict(list)
        for i in range(len(words)):
            g[uf.find(i)].append(i)
        for k in g.keys():
            g[k].sort(key=lambda i: words[i])
        sentence = text.split()
        ans = []
        t = []
        dfs(0)
        return ans
