class Solution:
    def isNumber(self, s: str) -> bool:
        i, j = 0, len(s) - 1
        while i < j and s[i] == " ":
            i += 1
        while i <= j and s[j] == " ":
            j -= 1
        if i > j:
            return False
        digit = dot = e = False
        while i <= j:
            if s[i] in "+-":
                if i and s[i - 1] not in " eE":
                    return False
            elif s[i].isdigit():
                digit = True
            elif s[i] == ".":
                if dot or e:
                    return False
                dot = True
            elif s[i] in "eE":
                if not digit or e:
                    return False
                e, digit = True, False
            else:
                return False
            i += 1
        return digit
