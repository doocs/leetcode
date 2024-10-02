class Solution:
    def countGoodSubstrings(self, s: str) -> int:
        ans = mask = l = 0
        for r, x in enumerate(map(lambda c: ord(c) - 97, s)):
            while mask >> x & 1:
                y = ord(s[l]) - 97
                mask ^= 1 << y
                l += 1
            mask |= 1 << x
            ans += int(r - l + 1 >= 3)
        return ans
