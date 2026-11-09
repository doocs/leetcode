class Solution:
    def validateStackSequences(self, pushed: list[int], popped: list[int]) -> bool:
        j, stk = 0, []
        for v in pushed:
            stk.append(v)
            while stk and stk[-1] == popped[j]:
                stk.pop()
                j += 1
        return j == len(pushed)
