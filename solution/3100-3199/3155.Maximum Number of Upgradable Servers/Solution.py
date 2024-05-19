class Solution:
    def maxUpgrades(
        self, count: List[int], upgrade: List[int], sell: List[int], money: List[int]
    ) -> List[int]:
        ans = []
        for cnt, cost, income, cash in zip(count, upgrade, sell, money):
            ans.append(min(cnt, (cnt * income + cash) // (cost + income)))
        return ans
