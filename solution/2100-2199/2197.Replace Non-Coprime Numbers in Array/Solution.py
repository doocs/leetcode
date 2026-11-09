class Solution:
    def replaceNonCoprimes(self, nums: list[int]) -> list[int]:
        stk = []
        for x in nums:
            stk.append(x)
            while len(stk) > 1:
                x, y = stk[-2:]
                g = gcd(x, y)
                if g == 1:
                    break
                stk.pop()
                stk[-1] = x * y // g
        return stk
