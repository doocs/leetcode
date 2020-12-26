class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        s = []
        q = 0
        for num in pushed:
            s.append(num)
            while s and s[-1] == popped[q]:
                s.pop()
                q += 1
        return not s
