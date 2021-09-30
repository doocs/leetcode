class Solution:
    def interpret(self, command: str) -> str:
        res = ''
        i, n = 0, len(command)
        while i < n:
            c = command[i]
            if c == 'G':
                res += c
                i += 1
            elif c == '(' and command[i + 1] != ')':
                res += 'al'
                i += 4
            else:
                res += 'o'
                i += 2
        return res
