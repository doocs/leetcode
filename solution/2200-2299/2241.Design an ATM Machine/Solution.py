class ATM:
    def __init__(self):
        self.cnt = [0] * 5
        self.m = [500, 200, 100, 50, 20]

    def deposit(self, banknotesCount: List[int]) -> None:
        for i, v in enumerate(banknotesCount[::-1]):
            self.cnt[i] += v

    def withdraw(self, amount: int) -> List[int]:
        ans = [0] * 5
        for i, n in enumerate(self.cnt):
            ans[i] = min(amount // self.m[i], n)
            amount -= self.m[i] * ans[i]
        if amount > 0:
            return [-1]
        for i, v in enumerate(ans):
            self.cnt[i] -= v
        return ans[::-1]


# Your ATM object will be instantiated and called as such:
# obj = ATM()
# obj.deposit(banknotesCount)
# param_2 = obj.withdraw(amount)
