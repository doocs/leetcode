class Solution:
    def countEval(self, s: str, result: int) -> int:
        @cache
        def dfs(s):
            res = [0] * 2
            if s in '01':
                res[int(s)] = 1
                return res
            for k, op in enumerate(s):
                if op in '&^|':
                    left, right = dfs(s[:k]), dfs(s[k + 1 :])
                    for i, v1 in enumerate(left):
                        for j, v2 in enumerate(right):
                            if op == '&':
                                v = i & j
                            elif op == '^':
                                v = i ^ j
                            elif op == '|':
                                v = i | j
                            res[v] += v1 * v2
            return res

        ans = dfs(s)
        return ans[result] if 0 <= result < 2 else 0
