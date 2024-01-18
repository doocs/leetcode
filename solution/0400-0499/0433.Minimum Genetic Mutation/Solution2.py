class Solution:
    def minMutation(self, start: str, end: str, bank: List[str]) -> int:
        def dfs(start, t):
            if start == end:
                nonlocal ans
                ans = min(ans, t)
                return
            for i, x in enumerate(start):
                for y in 'ACGT':
                    if x != y:
                        nxt = start[:i] + y + start[i + 1 :]
                        if nxt in s:
                            s.remove(nxt)
                            dfs(nxt, t + 1)

        s = set(bank)
        ans = inf
        dfs(start, 0)
        return -1 if ans == inf else ans
