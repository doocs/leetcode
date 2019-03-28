class Solution:

    def bitwiseComplement(self, N: int) -> int:
        if N == 0:
            return 1
        ans = 0
        index = 0
        while N > 1:
            if N % 2 == 0:
                ans += 2**index
            index += 1
            N = N // 2
        return ans
