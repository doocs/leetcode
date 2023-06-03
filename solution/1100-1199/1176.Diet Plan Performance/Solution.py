class Solution:
    def dietPlanPerformance(
        self, calories: List[int], k: int, lower: int, upper: int
    ) -> int:
        def check(s):
            if s < lower:
                return -1
            if s > upper:
                return 1
            return 0

        s, n = sum(calories[:k]), len(calories)
        ans = check(s)
        for i in range(k, n):
            s += calories[i] - calories[i - k]
            ans += check(s)
        return ans
