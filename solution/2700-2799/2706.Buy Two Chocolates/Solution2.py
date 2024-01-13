class Solution:
    def buyChoco(self, prices: List[int], money: int) -> int:
        a = b = inf
        for x in prices:
            if x < a:
                a, b = x, a
            elif x < b:
                b = x
        cost = a + b
        return money if money < cost else money - cost
