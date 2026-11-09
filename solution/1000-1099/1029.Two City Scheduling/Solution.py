class Solution:
    def twoCitySchedCost(self, costs: list[list[int]]) -> int:
        costs.sort(key=lambda x: x[0] - x[1])
        n = len(costs) >> 1
        return sum(costs[i][0] + costs[i + n][1] for i in range(n))
