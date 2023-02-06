class Solution:
    def permutation(self, s: str) -> List[str]:
        def dfs(i):
            if i == len(s) - 1:
                ans.append(''.join(cs))
                return
            vis = set()
            for j in range(i, len(s)):
                if cs[j] not in vis:
                    vis.add(cs[j])
                    cs[i], cs[j] = cs[j], cs[i]
                    dfs(i + 1)
                    cs[i], cs[j] = cs[j], cs[i]

        ans = []
        cs = list(s)
        dfs(0)
        return ans
