class Solution:
    def memLeak(self, memory1: int, memory2: int) -> List[int]:
        i = 1
        while 1:
            if memory1 >= memory2:
                if memory1 < i:
                    break
                memory1 -= i
            else:
                if memory2 < i:
                    break
                memory2 -= i
            i += 1
        return [i, memory1, memory2]
