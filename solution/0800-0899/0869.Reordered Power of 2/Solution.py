class Solution:
    def reorderedPowerOf2(self, n: int) -> bool:
        def convert(n):
            cnt = [0] * 10
            while n:
                n, v = divmod(n, 10)
                cnt[v] += 1
            return cnt

        i, s = 1, convert(n)
        while i <= 10**9:
            if convert(i) == s:
                return True
            i <<= 1
        return False
