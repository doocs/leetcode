class Solution:
    def dietPlanPerformance(
        self, calories: List[int], k: int, lower: int, upper: int
    ) -> int:
        s = list(accumulate(calories, initial=0))
        ans, n = 0, len(calories)
        for i in range(n - k + 1):
            t = s[i + k] - s[i]
            if t < lower:
                ans -= 1
            elif t > upper:
                ans += 1
        return ans
