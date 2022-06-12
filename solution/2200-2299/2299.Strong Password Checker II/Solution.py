class Solution:
    def strongPasswordCheckerII(self, password: str) -> bool:
        if len(password) < 8:
            return False
        ans = 0
        prev = '.'
        for c in password:
            if prev == c:
                return False
            prev = c
            if c.islower():
                ans |= 1
            if c.isupper():
                ans |= 2
            if c.isdigit():
                ans |= 4
            if c in '!@#$%^&*()-+':
                ans |= 8
        return ans == 15
