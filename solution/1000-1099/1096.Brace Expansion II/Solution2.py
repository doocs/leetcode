class Solution:
    def braceExpansionII(self, expression: str) -> List[str]:
        def expr(i: int):
            res, i = term(i)
            while i < len(expression) and expression[i] == ',':
                other, i = term(i + 1)
                res |= other
            return res, i

        def term(i: int):
            res = {''}
            while i < len(expression) and expression[i] not in ',}':
                if expression[i] == '{':
                    cur, i = expr(i + 1)
                    i += 1
                else:
                    j = i + 1
                    while j < len(expression) and expression[j].islower():
                        j += 1
                    cur = {expression[i:j]}
                    i = j
                res = {a + b for a in res for b in cur}
            return res, i

        ans, _ = expr(0)
        return sorted(ans)
