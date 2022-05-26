class Solution:
    def superPow(self, a: int, b: List[int]) -> int:
        MOD = 1337
        ans = 1
        for e in b[::-1]:
            ans = ans * pow(a, e, MOD) % MOD
            a = pow(a, 10, MOD)
        return ans
