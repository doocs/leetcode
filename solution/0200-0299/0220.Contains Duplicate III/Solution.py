from sortedcontainers import SortedSet


class Solution:
    def containsNearbyAlmostDuplicate(
        self, nums: List[int], indexDiff: int, valueDiff: int
    ) -> bool:
        s = SortedSet()
        for i, v in enumerate(nums):
            j = s.bisect_left(v - valueDiff)
            if j < len(s) and s[j] <= v + valueDiff:
                return True
            s.add(v)
            if i >= indexDiff:
                s.remove(nums[i - indexDiff])
        return False
