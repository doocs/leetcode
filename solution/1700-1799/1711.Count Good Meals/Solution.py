class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 10**9 + 7
        mx = max(deliciousness) << 1
        cnt = Counter()
        ans = 0
        for d in deliciousness:
            s = 1
            while s <= mx:
                ans = (ans + cnt[s - d]) % mod
                s <<= 1
            cnt[d] += 1
        return ans
