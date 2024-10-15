from sortedcontainers import SortedList


class Solution:
    def minimumCost(self, nums: List[int], k: int, dist: int) -> int:
        def l2r():
            nonlocal s
            x = l.pop()
            s -= x
            r.add(x)

        def r2l():
            nonlocal s
            x = r.pop(0)
            l.add(x)
            s += x

        k -= 1
        s = sum(nums[: dist + 2])
        l = SortedList(nums[1 : dist + 2])
        r = SortedList()
        while len(l) > k:
            l2r()
        ans = s
        for i in range(dist + 2, len(nums)):
            x = nums[i - dist - 1]
            if x in l:
                l.remove(x)
                s -= x
            else:
                r.remove(x)
            y = nums[i]
            if y < l[-1]:
                l.add(y)
                s += y
            else:
                r.add(y)
            while len(l) < k:
                r2l()
            while len(l) > k:
                l2r()
            ans = min(ans, s)
        return ans
