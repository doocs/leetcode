class Solution:
    def replaceSpace(self, s: str) -> str:
        ans = []
        for c in s:
            ans.append('%20' if c == ' ' else c)
        return ''.join(ans)
