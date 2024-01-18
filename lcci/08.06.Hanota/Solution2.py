class Solution:
    def hanota(self, A: List[int], B: List[int], C: List[int]) -> None:
        stk = [(len(A), A, B, C)]
        while stk:
            n, a, b, c = stk.pop()
            if n == 1:
                c.append(a.pop())
            else:
                stk.append((n - 1, b, a, c))
                stk.append((1, a, b, c))
                stk.append((n - 1, a, c, b))
