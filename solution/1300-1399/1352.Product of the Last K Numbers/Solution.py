class ProductOfNumbers:
    def __init__(self):
        self.pre_product = []

    def add(self, num: int) -> None:
        if num == 0:
            self.pre_product = []
            return
        if not self.pre_product:
            self.pre_product.append(1)
        self.pre_product.append(num * self.pre_product[-1])

    def getProduct(self, k: int) -> int:
        n = len(self.pre_product)
        return 0 if n <= k else self.pre_product[n - 1] // self.pre_product[n - k - 1]


# Your ProductOfNumbers object will be instantiated and called as such:
# obj = ProductOfNumbers()
# obj.add(num)
# param_2 = obj.getProduct(k)
