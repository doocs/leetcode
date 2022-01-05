class Solution:
    def modifyString(self, s: str) -> str:
        ans = list(s)
        for i, c in enumerate(ans):
            if c == '?':
                for cc in 'abc':
                    if i > 0 and ans[i - 1] == cc:
                        continue
                    if i < len(s) - 1 and ans[i + 1] == cc:
                        continue
                    ans[i] = cc
                    break
        return ''.join(ans)
