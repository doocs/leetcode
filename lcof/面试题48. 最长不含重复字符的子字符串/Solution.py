class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        vis = set()
        ans = j = 0
        for i, c in enumerate(s):
            while c in vis:
                vis.remove(s[j])
                j += 1
            vis.add(c)
            ans = max(ans, i - j + 1)
        return ans
