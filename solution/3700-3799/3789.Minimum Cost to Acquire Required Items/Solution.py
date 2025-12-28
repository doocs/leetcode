class Solution:
    def minimumCost(
        self, cost1: int, cost2: int, costBoth: int, need1: int, need2: int
    ) -> int:
        a = need1 * cost1 + need2 * cost2
        b = costBoth * max(need1, need2)
        mn = min(need1, need2)
        c = costBoth * mn + (need1 - mn) * cost1 + (need2 - mn) * cost2
        return min(a, b, c)
