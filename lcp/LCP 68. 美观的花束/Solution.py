class Solution:
    def beautifulBouquet(self, flowers: List[int], cnt: int) -> int:
        mod = 10**9 + 7
        d = Counter()
        ans = j = 0
        for i, x in enumerate(flowers):
            d[x] += 1
            while d[x] > cnt:
                d[flowers[j]] -= 1
                j += 1
            ans = (ans + i - j + 1) % mod
        return ans
