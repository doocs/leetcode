class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        ss = set()
        ans = j = 0
        for i, c in enumerate(s):
            while c in ss:
                ss.remove(s[j])
                j += 1
            ans = max(ans, i - j + 1)
            ss.add(c)
        return ans
