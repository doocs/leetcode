class Solution(object):
    def judgeSquareSum(self, c):
        i, j = 0, int(math.sqrt(c))
        while i <= j:
            s = i * i + j * j
            if s < c:
                i += 1
            elif s > c:
                j -= 1
            else:
                return True
        return False
