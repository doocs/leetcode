class Solution:
    def kConcatenationMaxSum(self, arr: List[int], k: int) -> int:
        s = mx_pre = mi_pre = mx_sub = 0
        for x in arr:
            s += x
            mx_pre = max(mx_pre, s)
            mi_pre = min(mi_pre, s)
            mx_sub = max(mx_sub, s - mi_pre)
        ans = mx_sub
        mod = 10**9 + 7
        if k == 1:
            return ans % mod
        mx_suf = s - mi_pre
        ans = max(ans, mx_pre + mx_suf)
        if s > 0:
            ans = max(ans, (k - 2) * s + mx_pre + mx_suf)
        return ans % mod
