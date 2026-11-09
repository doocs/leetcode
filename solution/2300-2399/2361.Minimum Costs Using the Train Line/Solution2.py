class Solution:
    def minimumCosts(
        self, regular: list[int], express: list[int], expressCost: int
    ) -> list[int]:
        n = len(regular)
        f, g = 0, inf
        cost = [0] * n
        for i, (a, b) in enumerate(zip(regular, express), 1):
            ff = min(f + a, g + a)
            gg = min(f + expressCost + b, g + b)
            f, g = ff, gg
            cost[i - 1] = min(f, g)
        return cost
