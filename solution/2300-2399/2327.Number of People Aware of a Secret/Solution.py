class Solution:
    def peopleAwareOfSecret(self, n: int, delay: int, forget: int) -> int:
        d = [0] * (n + 1010)
        cnt = [0] * (n + 1010)
        cnt[1] = 1
        for i in range(1, n + 1):
            if cnt[i]:
                d[i] += cnt[i]
                d[i + forget] -= cnt[i]
                nxt = i + delay
                while nxt < i + forget:
                    cnt[nxt] += cnt[i]
                    nxt += 1
        mod = 10**9 + 7
        return list(accumulate(d))[n] % mod
