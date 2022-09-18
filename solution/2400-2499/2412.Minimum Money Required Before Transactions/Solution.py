class Solution:
    def minimumMoney(self, transactions: List[List[int]]) -> int:
        s = sum(max(0, a - b) for a, b in transactions)
        ans = 0
        for a, b in transactions:
            if a > b:
                ans = max(ans, s + b)
            else:
                ans = max(ans, s + a)
        return ans
