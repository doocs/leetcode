class Solution:
    def isNumber(self, s: str) -> bool:
        if not s or not s.strip():
            return False
        s = s.strip()
        find_num = find_dot = find_e = False
        for i in range(len(s)):
            if s[i] == '+' or s[i] == '-':
                if i != 0 and s[i - 1] != 'e' and s[i - 1] != 'E':
                    return False
            elif s[i] >= '0' and s[i] <= '9':
                find_num = True
            elif s[i] == '.':
                if find_dot or find_e:
                    return False
                find_dot = True
            elif s[i] == 'e' or s[i] == 'E':
                if not find_num or find_e:
                    return False
                find_e = True
                find_num = False
            else:
                return False
        return find_num
