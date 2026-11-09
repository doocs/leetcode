class Solution:
    def maxUpgrades(
        self, count: list[int], upgrade: list[int], sell: list[int], money: list[int]
    ) -> list[int]:
        ans = []
        for cnt, cost, income, cash in zip(count, upgrade, sell, money):
            ans.append(min(cnt, (cnt * income + cash) // (cost + income)))
        return ans
