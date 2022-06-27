class Solution:
    def makeFancyString(self, s: str) -> str:
        ans = []
        for c in s:
            if len(ans) > 1 and ans[-1] == ans[-2] == c:
                continue
            ans.append(c)
        return ''.join(ans)
