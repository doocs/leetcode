class Solution:
    def maxAmount(
        self,
        initialCurrency: str,
        pairs1: List[List[str]],
        rates1: List[float],
        pairs2: List[List[str]],
        rates2: List[float],
    ) -> float:
        d1 = self.build(pairs1, rates1, initialCurrency)
        d2 = self.build(pairs2, rates2, initialCurrency)
        return max(d1.get(a, 0) / r2 for a, r2 in d2.items())

    def build(
        self, pairs: List[List[str]], rates: List[float], init: str
    ) -> Dict[str, float]:
        def dfs(a: str, v: float):
            d[a] = v
            for b, r in g[a]:
                if b not in d:
                    dfs(b, v * r)

        g = defaultdict(list)
        for (a, b), r in zip(pairs, rates):
            g[a].append((b, r))
            g[b].append((a, 1 / r))
        d = {}
        dfs(init, 1)
        return d
