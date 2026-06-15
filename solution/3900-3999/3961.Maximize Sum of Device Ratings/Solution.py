class Solution:
    def maxRatings(self, units: List[List[int]]) -> int:
        n = len(units[0])
        if n == 1:
            return sum(x[0] for x in units)

        ans = 0
        mn = mn2 = inf
        for x in units:
            x.sort()
            ans += x[1]
            mn2 = min(mn2, x[1])
            mn = min(mn, x[0])
        ans -= mn2 - mn
        return ans
