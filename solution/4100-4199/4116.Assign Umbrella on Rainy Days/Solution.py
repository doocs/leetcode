class Solution:
    def assignUmbrella(self, rain: list[int]) -> list[str]:
        n = len(rain)
        a = b = 0
        ans = [None] * n
        for i, x in enumerate(rain):
            if x == 0:
                ans[i] = "None"
            elif a <= b and i < n - 1 and rain[i + 1] == 1:
                ans[i] = "Alice"
                a += 1
            else:
                ans[i] = "Bob"
                b += 1
        return ans
