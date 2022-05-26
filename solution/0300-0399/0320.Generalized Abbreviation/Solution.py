class Solution:
    def generateAbbreviations(self, word: str) -> List[str]:
        def dfs(s, t):
            if not s:
                ans.append(''.join(t))
                return
            for i in range(1, len(s) + 1):
                t.append(str(i))
                if i < len(s):
                    t.append(s[i])
                    dfs(s[i + 1 :], t)
                    t.pop()
                else:
                    dfs(s[i:], t)
                t.pop()

            t.append(s[0])
            dfs(s[1:], t)
            t.pop()

        ans = []
        dfs(word, [])
        return ans
