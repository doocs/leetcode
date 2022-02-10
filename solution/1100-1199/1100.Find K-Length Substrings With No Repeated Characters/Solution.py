class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        ans = j = 0
        mp = {}
        for i, c in enumerate(s):
            if c in mp:
                j = max(j, mp[c] + 1)
            mp[c] = i
            if i - j + 1 >= k:
                ans += 1
        return ans
