class Solution:
    def checkValidString(self, s: str) -> bool:
        n = len(s)
        left, asterisk = 0, 0
        for i in range(n):
            if s[i] == "(":
                left += 1
            elif s[i] == ")":
                if left > 0:
                    left -= 1
                elif asterisk > 0:
                    asterisk -= 1
                else:
                    return False
            else:
                asterisk += 1
        right, asterisk = 0, 0
        for i in range(n - 1, -1, -1):
            if s[i] == ")":
                right += 1
            elif s[i] == "(":
                if right > 0:
                    right -= 1
                elif asterisk > 0:
                    asterisk -= 1
                else:
                    return False
            else:
                asterisk += 1
        return True
