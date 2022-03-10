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
        def check(text, fontSize, w, h, fontInfo) -> bool:
            if fontInfo.getHeight(fontSize) > h:
                return False
            width = 0
            for ch in text:
                width += fontInfo.getWidth(fontSize, ch)
                if width > w:
                    return False
            return True

        left, right = 0, len(fonts) - 1
        while left < right:
            mid = (left + right + 1) >> 1
            fontSize = fonts[mid]
            if check(text, fontSize, w, h, fontInfo):
                left = mid
            else:
                right = mid - 1
        return fonts[left] if check(text, fonts[left], w, h, fontInfo) else -1
