class Solution:
    def calculateTax(self, brackets: List[List[int]], income: int) -> float:
        ans = idx = 0
        prev = 0
        while income:
            a, b = brackets[idx]
            d = a - prev
            ans += min(d, income) * b / 100
            if income <= d:
                break
            income -= d
            idx += 1
            prev = a
        return ans
