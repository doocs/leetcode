class Solution:
    def strongPasswordCheckerII(self, password: str) -> bool:
        if len(password) < 8:
            return False
        ans = 0
        for i, c in enumerate(password):
            if i and password[i - 1] == c:
                return False
            if c.islower():
                ans |= 1
            elif c.isupper():
                ans |= 2
            elif c.isdigit():
                ans |= 4
            else:
                ans |= 8
        return ans == 15
