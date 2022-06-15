class Solution:
    @cache
    def countDigitOne(self, n: int) -> int:
        if n < 1:
            return 0
        s = str(n)
        high = int(s[0])
        base = pow(10, len(s) - 1)
        lows = n % base
        return (
            self.countDigitOne(base - 1) + lows + 1 + self.countDigitOne(lows)
            if high == 1
            else high * self.countDigitOne(base - 1) + base + self.countDigitOne(lows)
        )
