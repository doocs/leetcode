from sortedcontainers import SortedDict


class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        if len(nums) % k != 0:
            return False
        sd = SortedDict()
        for h in nums:
            if h in sd:
                sd[h] += 1
            else:
                sd[h] = 1
        while sd:
            v = sd.peekitem(0)[0]
            for i in range(v, v + k):
                if i not in sd:
                    return False
                if sd[i] == 1:
                    sd.pop(i)
                else:
                    sd[i] -= 1
        return True
