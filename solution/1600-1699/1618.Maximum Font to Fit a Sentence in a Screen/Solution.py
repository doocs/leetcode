# """
# This is FontInfo's API interface.
# You should not implement it, or speculate about its implementation
# """
# class FontInfo(object):
#    Return the width of char ch when fontSize is used.
#    def getWidth(self, fontSize, ch):
#        """
#        :type fontSize: int
#        :type ch: char
#        :rtype int
#        """
#
#    def getHeight(self, fontSize):
#        """
#        :type fontSize: int
#        :rtype int
#        """
class Solution:
    def maxFont(
        self, text: str, w: int, h: int, fonts: List[int], fontInfo: 'FontInfo'
    ) -> int:
        def check(size):
            if fontInfo.getHeight(size) > h:
                return False
            return sum(fontInfo.getWidth(size, c) for c in text) <= w

        left, right = 0, len(fonts) - 1
        ans = -1
        while left < right:
            mid = (left + right + 1) >> 1
            if check(fonts[mid]):
                left = mid
            else:
                right = mid - 1
        return fonts[left] if check(fonts[left]) else -1
