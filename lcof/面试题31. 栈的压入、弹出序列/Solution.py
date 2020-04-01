class Solution:
    def validateStackSequences(self, pushed: List[int],
                               popped: List[int]) -> bool:
        t = []
        for num in popped:
            while len(t) == 0 or t[-1] != num:
                if len(pushed) == 0:
                    return False
                t.append(pushed[0])
                pushed = pushed[1:]
            t.pop()
        return True
