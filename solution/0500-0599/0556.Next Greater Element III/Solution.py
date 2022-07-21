class Solution:
    def nextGreaterElement(self, n: int) -> int:
        cs = list(str(n))
        n = len(cs)
        i, j = n - 2, n - 1
        while i >= 0 and cs[i] >= cs[i + 1]:
            i -= 1
        if i < 0:
            return -1
        while cs[i] >= cs[j]:
            j -= 1
        cs[i], cs[j] = cs[j], cs[i]
        cs[i + 1 :] = cs[i + 1 :][::-1]
        ans = int(''.join(cs))
        return -1 if ans > 2**31 - 1 else ans
