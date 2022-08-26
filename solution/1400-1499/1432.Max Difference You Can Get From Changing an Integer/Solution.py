class Solution:
    def maxDiff(self, num: int) -> int:
        a, b = str(num), str(num)
        for c in a:
            if c != '9':
                a = a.replace(c, '9')
                break
        for i, c in enumerate(b):
            if i == 0:
                if c != '1':
                    b = b.replace(c, '1')
                    break
            else:
                if c != '0' and c != b[0]:
                    b = b.replace(c, '0')
                    break
        return int(a) - int(b)
