class Solution:
    def applySubstitutions(self, replacements: List[List[str]], text: str) -> str:
        def dfs(s: str) -> str:
            i = s.find("%")
            if i == -1:
                return s
            j = s.find("%", i + 1)
            if j == -1:
                return s
            key = s[i + 1 : j]
            replacement = dfs(d[key])
            return s[:i] + replacement + dfs(s[j + 1 :])

        d = {s: t for s, t in replacements}
        return dfs(text)
