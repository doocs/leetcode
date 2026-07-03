class Cashier:

    def __init__(self, n: int, discount: int, products: List[int], prices: List[int]):
        self.i = 0
        self.n = n
        self.discount = discount
        self.d = {a: b for a, b in zip(products, prices)}

    def getBill(self, product: List[int], amount: List[int]) -> float:
        self.i = (self.i + 1) % self.n
        x = sum(self.d[a] * b for a, b in zip(product, amount))
        if self.i == 0:
            return x - (self.discount * x) / 100
        return x


# Your Cashier object will be instantiated and called as such:
# obj = Cashier(n, discount, products, prices)
# param_1 = obj.getBill(product,amount)
