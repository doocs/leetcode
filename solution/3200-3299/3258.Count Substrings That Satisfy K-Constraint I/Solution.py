class Solution:
    def countKConstraintSubstrings(self, s: str, k: int) -> int:
        cnt0 = cnt1 = 0
        ans = l = 0
        for r, c in enumerate(s):
            cnt0 += int(c) ^ 1
            cnt1 += int(c)
            while cnt0 > k and cnt1 > k:
                cnt0 -= int(s[l]) ^ 1
                cnt1 -= int(s[l])
                l += 1
            ans += r - l + 1
        return ans
