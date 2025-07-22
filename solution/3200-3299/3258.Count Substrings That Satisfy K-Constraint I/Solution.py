class Solution:
    def countKConstraintSubstrings(self, s: str, k: int) -> int:
        cnt = [0, 0]
        ans = l = 0
        for r, x in enumerate(map(int, s)):
            cnt[x] += 1
            while cnt[0] > k and cnt[1] > k:
                cnt[int(s[l])] -= 1
                l += 1
            ans += r - l + 1
        return ans
