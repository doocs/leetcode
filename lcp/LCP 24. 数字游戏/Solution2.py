from sortedcontainers import SortedList


class Solution:
    def numsGame(self, nums: List[int]) -> List[int]:
        l = SortedList()
        r = SortedList()
        s = t = 0
        mod = 10**9 + 7
        ans = []
        for i, x in enumerate(nums):
            x -= i
            r.add(x)
            t += x
            x = r.pop(0)
            t -= x
            l.add(x)
            s += x
            while len(l) - len(r) > 1:
                x = l.pop()
                s -= x
                r.add(x)
                t += x
            v = len(l) * l[-1] - s + t - len(r) * l[-1]
            ans.append(v % mod)
        return ans
