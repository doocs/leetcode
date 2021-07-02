class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        costs.sort()
        ans, n = 0, len(costs)
        for i in range(n):
            if coins < costs[i]:
                break
            else:
                ans += 1
                coins -= costs[i]
        return ans
