class Solution:
    def isValid(self, s: str) -> bool:
        q = []
        parentheses = {'()', '[]', '{}'}
        for ch in s:
            if ch in '([{':
                q.append(ch)
            elif not q or q.pop() + ch not in parentheses:
                return False
        return not q
