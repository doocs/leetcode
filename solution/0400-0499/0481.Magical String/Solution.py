class Solution:
    def magicalString(self, n: int) -> int:
        s = list('1221121')
        i = 5
        while len(s) < n:
            if s[i] == '1':
                s.append('2' if s[-1] == '1' else '1')
            else:
                s.extend(list('22' if s[-1] == '1' else '11'))
            i += 1
        return s[:n].count('1')
