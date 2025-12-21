class Solution:
    def minCost(self, s: str, cost: List[int]) -> int:
        tot = 0
        g = defaultdict(int)
        for c, v in zip(s, cost):
            tot += v
            g[c] += v
        return min(tot - x for x in g.values())
