class Solution:
    def countLargestGroup(self, n: int) -> int:
        cnt = Counter()
        ans, mx = 0, 0
        for i in range(1, n + 1):
            t = sum(int(v) for v in str(i))
            cnt[t] += 1
            if mx < cnt[t]:
                mx = cnt[t]
                ans = 1
            elif mx == cnt[t]:
                ans += 1
        return ans
