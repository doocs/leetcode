mx = 10**5 + 1
mod = 10**9 + 7
pow10 = [1] * mx
for i in range(1, mx):
    pow10[i] = pow10[i - 1] * 10 % mod


class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        sum_d = [0] * (n + 1)
        cnt_n0 = [0] * (n + 1)
        p = [0] * (n + 1)
        for i, d in enumerate(map(int, s), 1):
            sum_d[i] = sum_d[i - 1] + d
            cnt_n0[i] = cnt_n0[i - 1] + int(d > 0)
            p[i] = (p[i - 1] * 10 + d) % mod if d else p[i - 1]

        ans = []
        for l, r in queries:
            n0 = cnt_n0[r + 1] - cnt_n0[l]
            sd = sum_d[r + 1] - sum_d[l]
            x = p[r + 1] - p[l] * pow10[n0] % mod
            ans.append(x * sd % mod)
        return ans
