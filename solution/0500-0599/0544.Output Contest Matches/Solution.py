class Solution:
    def findContestMatch(self, n: int) -> str:
        s = [str(i + 1) for i in range(n)]
        while n > 1:
            for i in range(n >> 1):
                s[i] = f"({s[i]},{s[n - i - 1]})"
            n >>= 1
        return s[0]
