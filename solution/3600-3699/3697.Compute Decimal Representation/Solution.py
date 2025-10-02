class Solution:
    def decimalRepresentation(self, n: int) -> List[int]:
        ans = []
        p = 1
        while n:
            n, v = divmod(n, 10)
            if v:
                ans.append(p * v)
            p *= 10
        ans.reverse()
        return ans
