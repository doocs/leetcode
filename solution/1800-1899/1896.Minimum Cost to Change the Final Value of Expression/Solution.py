class Solution:
    def minOperationsToFlip(self, expression: str) -> int:
        def merge(a, b, op):
            v1, c1 = a
            v2, c2 = b
            if op == '&':
                val = v1 & v2
                if v1 == 1 and v2 == 1:
                    cost = min(c1, c2)
                elif v1 == 0 and v2 == 0:
                    cost = min(c1 + c2, 1 + min(c1, c2))
                else:
                    cost = min(c1 if v1 == 0 else c2, 1)
            else:
                val = v1 | v2
                if v1 == 0 and v2 == 0:
                    cost = min(c1, c2)
                elif v1 == 1 and v2 == 1:
                    cost = min(c1 + c2, 1 + min(c1, c2))
                else:
                    cost = min(c1 if v1 == 1 else c2, 1)
            return val, cost

        nums = []
        ops = []

        def apply():
            b = nums.pop()
            a = nums.pop()
            nums.append(merge(a, b, ops.pop()))

        for c in expression:
            if c == '(':
                ops.append(c)
            elif c in '01':
                nums.append((int(c), 1))
            elif c in '&|':
                while ops and ops[-1] in '&|':
                    apply()
                ops.append(c)
            else:
                while ops[-1] != '(':
                    apply()
                ops.pop()
        while ops:
            apply()
        return nums[-1][1]
