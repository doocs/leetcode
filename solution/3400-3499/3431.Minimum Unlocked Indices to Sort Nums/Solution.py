class Solution:
    def minUnlockedIndices(self, nums: List[int], locked: List[int]) -> int:
        n = len(nums)
        first2 = first3 = n
        last1 = last2 = -1
        for i, x in enumerate(nums):
            if x == 1:
                last1 = i
            elif x == 2:
                first2 = min(first2, i)
                last2 = i
            else:
                first3 = min(first3, i)
        if first3 < last1:
            return -1
        return sum(
            st and (first2 <= i < last1 or first3 <= i < last2)
            for i, st in enumerate(locked)
        )
