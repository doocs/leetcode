class Solution:
    def beautySum(self, s: str) -> int:
        ans, n = 0, len(s)
        for i in range(n):
            counter = Counter()
            for j in range(i, n):
                counter[s[j]] += 1
                t = [v for v in counter.values() if v]
                ans += max(t) - min(t)
        return ans
