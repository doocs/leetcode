class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        l = SortedList()
        r = SortedList()
        s1 = s2 = 0
        ans = inf
        for i, x in enumerate(nums):
            l.add(x)
            s1 += x
            y = l.pop()
            s1 -= y
            r.add(y)
            s2 += y
            if len(r) - len(l) > 1:
                y = r.pop(0)
                s2 -= y
                l.add(y)
                s1 += y
            if i >= k - 1:
                ans = min(ans, s2 - r[0] * len(r) + r[0] * len(l) - s1)
                j = i - k + 1
                if nums[j] in r:
                    r.remove(nums[j])
                    s2 -= nums[j]
                else:
                    l.remove(nums[j])
                    s1 -= nums[j]
        return ans
