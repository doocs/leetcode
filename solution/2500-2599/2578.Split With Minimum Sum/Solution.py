class Solution:
    def splitNum(self, num: int) -> int:
        cnt = Counter()
        n = 0
        while num:
            cnt[num % 10] += 1
            num //= 10
            n += 1
        ans = [0] * 2
        j = 0
        for i in range(n):
            while cnt[j] == 0:
                j += 1
            cnt[j] -= 1
            ans[i & 1] = ans[i & 1] * 10 + j
        return sum(ans)
