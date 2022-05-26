class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i, t):
            if i == len(t):
                ans.append(''.join(t))
                return
            dfs(i + 1, t)
            if t[i].isalpha():
                t[i] = t[i].upper() if t[i].islower() else t[i].lower()
                dfs(i + 1, t)

        ans = []
        t = list(s)
        dfs(0, t)
        return ans
