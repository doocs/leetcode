class Solution:
    def maximumScore(self, scores: List[int], edges: List[List[int]]) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        for k in g.keys():
            g[k] = nlargest(3, g[k], key=lambda x: scores[x])
        ans = -1
        for a, b in edges:
            for c in g[a]:
                for d in g[b]:
                    if b != c != d != a:
                        t = scores[a] + scores[b] + scores[c] + scores[d]
                        ans = max(ans, t)
        return ans
