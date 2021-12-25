class Solution:
    def minCost(self, costs: List[List[int]]) -> int:
        r, g, b = 0, 0, 0
        for cost in costs:
            _r, _g, _b = r, g, b
            r = min(_g, _b) + cost[0]
            g = min(_r, _b) + cost[1]
            b = min(_r, _g) + cost[2]
        return min(r, g, b)
