class Solution:
    def maximizeTheProfit(self, n: int, offers: List[List[int]]) -> int:
        offers.sort(key=lambda x: x[1])
        f = [0] * (len(offers) + 1)
        g = [x[1] for x in offers]
        for i, (s, _, v) in enumerate(offers, 1):
            j = bisect_left(g, s)
            f[i] = max(f[i - 1], f[j] + v)
        return f[-1]
