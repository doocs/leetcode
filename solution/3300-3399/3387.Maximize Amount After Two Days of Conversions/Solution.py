class Solution:
    def maxAmount(
        self,
        initialCurrency: str,
        pairs1: list[list[str]],
        rates1: list[float],
        pairs2: list[list[str]],
        rates2: list[float],
    ) -> float:
        d1 = self.build(pairs1, rates1, initialCurrency)
        d2 = self.build(pairs2, rates2, initialCurrency)
        return max(d1.get(a, 0) / r2 for a, r2 in d2.items())

    def build(
        self, pairs: list[list[str]], rates: list[float], init: str
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
