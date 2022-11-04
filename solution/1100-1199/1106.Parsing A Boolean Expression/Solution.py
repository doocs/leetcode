class Solution:
    def parseBoolExpr(self, expression: str) -> bool:
        stk = []
        for c in expression:
            if c in 'tf!&|':
                stk.append(c)
            elif c == ')':
                t = f = 0
                while stk[-1] in 'tf':
                    t += stk[-1] == 't'
                    f += stk[-1] == 'f'
                    stk.pop()
                match stk.pop():
                    case '!':
                        c = 't' if f else 'f'
                    case '&':
                        c = 'f' if f else 't'
                    case '|':
                        c = 't' if t else 'f'
                stk.append(c)
        return stk[0] == 't'
