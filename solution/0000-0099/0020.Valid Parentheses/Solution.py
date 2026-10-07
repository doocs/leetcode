class Solution:
    def isValid(self, s: str) -> bool:
        stk = []
        d = {'(': ')', '[': ']', '{': '}'}
        for c in s:
            if c in d:
                stk.append(d[c])
            elif not stk or stk.pop() != c:
                return False
        return not stk
