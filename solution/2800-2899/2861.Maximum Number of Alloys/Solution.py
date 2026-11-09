class Solution:
    def maxNumberOfAlloys(
        self,
        n: int,
        k: int,
        budget: int,
        composition: list[list[int]],
        stock: list[int],
        cost: list[int],
    ) -> int:
        ans = 0
        for c in composition:
            l, r = 0, budget + stock[0]
            while l < r:
                mid = (l + r + 1) >> 1
                s = sum(max(0, mid * x - y) * z for x, y, z in zip(c, stock, cost))
                if s <= budget:
                    l = mid
                else:
                    r = mid - 1
            ans = max(ans, l)
        return ans
