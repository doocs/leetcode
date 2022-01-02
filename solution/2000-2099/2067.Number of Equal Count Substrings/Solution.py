class Solution:
    def equalCountSubstrings(self, s: str, count: int) -> int:
        n = len(s)
        if count > n:
            return 0
        counter = [[0] * 26 for _ in range(n + 1)]

        def check(i, j):
            c1 = counter[i]
            c2 = counter[j + 1]
            for k in range(26):
                if c2[k] == 0 or c1[k] == c2[k]:
                    continue
                if c2[k] - c1[k] != count:
                    return False
            return True

        ans = 0
        for i, c in enumerate(s):
            idx = ord(c) - ord('a')
            for j in range(26):
                counter[i + 1][j] = counter[i][j]
            counter[i + 1][idx] = counter[i][idx] + 1
            l = 0
            for _ in range(26):
                l += count
                j = i - l + 1
                if j < 0:
                    break
                ans += check(j, i)
        return ans
