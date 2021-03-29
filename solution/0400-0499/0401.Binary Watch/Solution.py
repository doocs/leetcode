class Solution:
    def readBinaryWatch(self, num: int) -> List[str]:
        return ['{:d}:{:02d}'.format(i, j) for i in range(12) for j in range(60) if (bin(i) + bin(j)).count('1') == num]
