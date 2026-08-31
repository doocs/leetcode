class Solution:
    def sumDecoded(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        ans = 0
        for v in nums:
            d, w = divmod(v, 10)
            s = str(d)
            x = int(s[:w])
            y = int(s[w:])
            ans = (ans + pow(x, y, mod)) % mod
        return ans
