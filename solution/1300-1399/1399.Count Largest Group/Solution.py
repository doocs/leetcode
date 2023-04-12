class Solution:
    def countLargestGroup(self, n: int) -> int:
        cnt = Counter()
        ans = mx = 0
        for i in range(1, n + 1):
            s = 0
            while i:
                s += i % 10
                i //= 10
            cnt[s] += 1
            if mx < cnt[s]:
                mx = cnt[s]
                ans = 1
            elif mx == cnt[s]:
                ans += 1
        return ans
