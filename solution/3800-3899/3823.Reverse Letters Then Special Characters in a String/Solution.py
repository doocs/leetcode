class Solution:
    def reverseByType(self, s: str) -> str:
        a = []
        b = []
        for c in s:
            if c.isalpha():
                a.append(c)
            else:
                b.append(c)
        return ''.join(a.pop() if c.isalpha() else b.pop() for c in s)
