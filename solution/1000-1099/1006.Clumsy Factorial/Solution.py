class Solution:

    def clumsy(self, N: int) -> int:
        s = ''
        calc = ['*', '//', '+', '-']
        i = 0
        while N != 1:
            s = s + str(N) + calc[i]
            i += 1
            i %= 4
            N -= 1
        s += '1'
        return eval(s)
