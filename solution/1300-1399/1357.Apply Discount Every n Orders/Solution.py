class Cashier:
    def __init__(self, n: int, discount: int, products: List[int], prices: List[int]):
        self.i = 0
        self.n = n
        self.discount = discount
        self.d = {product: price for product, price in zip(products, prices)}

    def getBill(self, product: List[int], amount: List[int]) -> float:
        self.i += 1
        discount = self.discount if self.i % self.n == 0 else 0
        ans = 0
        for p, a in zip(product, amount):
            x = self.d[p] * a
            ans += x - (discount * x) / 100
        return ans


# Your Cashier object will be instantiated and called as such:
# obj = Cashier(n, discount, products, prices)
# param_1 = obj.getBill(product,amount)
