class Solution:
    def canEat(self, candiesCount: list[int], queries: list[list[int]]) -> list[bool]:
        s = list(accumulate(candiesCount, initial=0))
        ans = []
        for t, day, mx in queries:
            least, most = day, (day + 1) * mx
            ans.append(least < s[t + 1] and most > s[t])
        return ans
