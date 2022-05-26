class Solution:
    def addBinary(self, a: str, b: str) -> str:
        x, y = len(a) - 1, len(b) - 1
        arr = []
        carry = 0
        while x >= 0 or y >= 0:
            if x >= 0:
                if a[x] == '1':
                    carry += 1
                x -= 1
            if y >= 0:
                if b[y] == '1':
                    carry += 1
                y -= 1
            arr.append(chr((carry & 1) + ord('0')))
            carry >>= 1
        if carry == 1:
            arr.append('1')
        return ''.join(reversed(arr))
