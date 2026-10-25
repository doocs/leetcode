class Solution:
    def calc(self, s: str) -> list[list[int]]:
        n = len(s)
        f = [[0] * 27 for _ in range(n)]
        for k in range(1, 27):
            cnt = [0] * 26
            l = distinct = 0
            for r, c in enumerate(s):
                c = ord(c) - ord("a")
                if cnt[c] == 0:
                    distinct += 1
                cnt[c] += 1
                while distinct > k:
                    c = ord(s[l]) - ord("a")
                    cnt[c] -= 1
                    if cnt[c] == 0:
                        distinct -= 1
                    l += 1
                f[r][k] = r - l + 1
        return f

    def countMagicalPairs(self, s: str) -> int:
        n = len(s)
        left = self.calc(s)
        right = self.calc(s[::-1])
        ans = 0
        for j in range(n - 1):
            r = n - 2 - j
            for k in range(1, 27):
                ans += (left[j][k] - left[j][k - 1]) * (right[r][k] - right[r][k - 1])
        return ans
