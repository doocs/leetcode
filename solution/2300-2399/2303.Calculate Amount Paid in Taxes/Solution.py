class Solution:
    def calculateTax(self, brackets: List[List[int]], income: int) -> float:
        ans = prev = 0
        for upper, percent in brackets:
            ans += max(0, min(income, upper) - prev) * percent
            prev = upper
        return ans / 100
