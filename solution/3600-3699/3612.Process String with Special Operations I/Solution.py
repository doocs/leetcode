class Solution:
    def processStr(self, s: str) -> str:
        result = []
        for c in s:
            if c.isalpha():
                result.append(c)
            elif c == "*" and result:
                result.pop()
            elif c == "#":
                result.extend(result)
            elif c == "%":
                result.reverse()
        return "".join(result)
