class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        costs.sort()
        ans = 0
        for c in costs:
            if coins < c:
                break
            else:
                ans += 1
                coins -= c
        return ans
