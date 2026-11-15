class Solution:
    def numOfSubarrays(self, arr: list[int]) -> int:
        cnt = [1, 0]
        ans = s = 0
        mod = 10**9 + 7
        for x in arr:
            s = (s + x) % 2
            ans = (ans + cnt[s ^ 1]) % mod
            cnt[s] += 1
        return ans
