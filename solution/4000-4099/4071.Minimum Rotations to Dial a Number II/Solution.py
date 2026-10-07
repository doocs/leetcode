class Solution:
    def minRotations(self, n: int, s: str) -> int:
        def dist(a: str, b: str) -> int:
            d = abs(ord(a) - ord(b))
            return min(d, 10 - d)

        total = sum(dist(a, b) for a, b in pairwise(s))

        first = s[0]
        last = s[-1]
        to_first = dist("0", first)
        ans = total + dist("0", last)

        for pre, cur in pairwise(s):
            ans = min(ans, total - dist(pre, cur) + to_first + dist(pre, last))

        return ans
