class Solution:
    def possibleStringCount(self, word: str, k: int) -> int:
        mod = 10**9 + 7
        nums = []
        ans = 1
        cur = 0
        for i, c in enumerate(word):
            cur += 1
            if i == len(word) - 1 or c != word[i + 1]:
                if cur > 1:
                    if k > 0:
                        nums.append(cur - 1)
                    ans = ans * cur % mod
                cur = 0
                k -= 1
        if k < 1:
            return ans
        m = len(nums)
        f = [[0] * k for _ in range(m + 1)]
        f[0][0] = 1
        for i, x in enumerate(nums, 1):
            s = list(accumulate(f[i - 1], initial=0))
            for j in range(k):
                f[i][j] = (s[j + 1] - s[j - min(x, j)] + mod) % mod
        return (ans - sum(f[m][j] for j in range(k))) % mod
