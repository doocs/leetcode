class Solution:
    def maxTotal(self, value: List[int], limit: List[int]) -> int:
        g = defaultdict(list)
        for v, lim in zip(value, limit):
            g[lim].append(v)
        ans = 0
        for lim, vs in g.items():
            vs.sort()
            ans += sum(vs[-lim:])
        return ans
