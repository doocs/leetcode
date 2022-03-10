class Solution:
    def permutation(self, s: str) -> List[str]:
        def dfs(x):
            if x == len(s) - 1:
                res.append("".join(chars))
                return
            t = set()
            for i in range(x, len(s)):
                if chars[i] in t:
                    continue
                t.add(chars[i])
                chars[i], chars[x] = chars[x], chars[i]
                dfs(x + 1)
                chars[i], chars[x] = chars[x], chars[i]

        chars, res = list(s), []
        dfs(0)
        return res
