class Solution:
    def shoppingOffers(
        self, price: List[int], special: List[List[int]], needs: List[int]
    ) -> int:
        def total(price, needs):
            return sum(price[i] * needs[i] for i in range(len(needs)))

        ans = total(price, needs)
        t = []
        for offer in special:
            t.clear()
            for j in range(len(needs)):
                if offer[j] > needs[j]:
                    t.clear()
                    break
                t.append(needs[j] - offer[j])
            if t:
                ans = min(ans, offer[-1] + self.shoppingOffers(price, special, t))
        return ans
