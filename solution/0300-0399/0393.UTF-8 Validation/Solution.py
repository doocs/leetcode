class Solution:
    def validUtf8(self, data: List[int]) -> bool:
        cnt = 0
        for v in data:
            if cnt > 0:
                if v >> 6 != 0b10:
                    return False
                cnt -= 1
            elif v >> 7 == 0:
                cnt = 0
            elif v >> 5 == 0b110:
                cnt = 1
            elif v >> 4 == 0b1110:
                cnt = 2
            elif v >> 3 == 0b11110:
                cnt = 3
            else:
                return False
        return cnt == 0
