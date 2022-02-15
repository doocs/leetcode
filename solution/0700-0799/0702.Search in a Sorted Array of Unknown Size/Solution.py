# """
# This is ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
# class ArrayReader:
#    def get(self, index: int) -> int:


class Solution:
    def search(self, reader, target):
        """
        :type reader: ArrayReader
        :type target: int
        :rtype: int
        """
        left, right = 0, 20000
        while left < right:
            mid = (left + right) >> 1
            if reader.get(mid) >= target:
                right = mid
            else:
                left = mid + 1
        return left if reader.get(left) == target else -1
