class Solution:
    def findValidPair(self, s: str) -> str:
        cnt = [0] * 10
        for x in map(int, s):
            cnt[x] += 1
        for x, y in pairwise(map(int, s)):
            if x != y and cnt[x] == x and cnt[y] == y:
                return f"{x}{y}"
        return ""
