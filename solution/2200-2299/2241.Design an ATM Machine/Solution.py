class ATM:
    def __init__(self):
        self.d = [20, 50, 100, 200, 500]
        self.m = len(self.d)
        self.cnt = [0] * self.m

    def deposit(self, banknotesCount: List[int]) -> None:
        for i, x in enumerate(banknotesCount):
            self.cnt[i] += x

    def withdraw(self, amount: int) -> List[int]:
        ans = [0] * self.m
        for i in reversed(range(self.m)):
            ans[i] = min(amount // self.d[i], self.cnt[i])
            amount -= ans[i] * self.d[i]
        if amount > 0:
            return [-1]
        for i, x in enumerate(ans):
            self.cnt[i] -= x
        return ans


# Your ATM object will be instantiated and called as such:
# obj = ATM()
# obj.deposit(banknotesCount)
# param_2 = obj.withdraw(amount)
