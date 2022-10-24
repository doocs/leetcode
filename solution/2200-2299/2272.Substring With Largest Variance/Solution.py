class Solution:
    def largestVariance(self, s: str) -> int:
        ans = 0
        for a, b in permutations(ascii_lowercase, 2):
            if a == b:
                continue
            f = [0, -inf]
            for c in s:
                if c == a:
                    f[0], f[1] = f[0] + 1, f[1] + 1
                elif c == b:
                    f[1] = max(f[1] - 1, f[0] - 1)
                    f[0] = 0
                if ans < f[1]:
                    ans = f[1]
        return ans
