class Solution:
    def maximumSwap(self, num: int) -> int:
        chars = list(str(num))
        n = len(chars)
        for i in range(n - 1):
            mx = i + 1
            for j in range(i + 1, n):
                if ord(chars[j]) >= ord(chars[mx]):
                    mx = j
            if ord(chars[i]) < ord(chars[mx]):
                chars[i], chars[mx] = chars[mx], chars[i]
                break
        return int(''.join(chars))
