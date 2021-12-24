class Solution:
    def minDeletions(self, s: str) -> int:
        counter = Counter(s)
        vals = [v for v in counter.values()]
        vals.sort(reverse=True)
        ans = 0
        for i in range(1, len(vals)):
            while vals[i] >= vals[i - 1] and vals[i] > 0:
                vals[i] -= 1
                ans += 1
        return ans
