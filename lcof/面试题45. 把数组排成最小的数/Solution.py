import functools


class Solution:
    def minNumber(self, nums: List[int]) -> str:
        if not nums:
            return ''

        def compare(s1, s2):
            if s1 + s2 < s2 + s1:
                return -1
            if s1 + s2 > s2 + s1:
                return 1
            return 0

        return ''.join(
            sorted([str(x) for x in nums], key=functools.cmp_to_key(compare))
        )
