class Solution:
    def firstUniqChar(self, s: str) -> int:
        chars = {}
        for ch in s:
            ch = ord(ch)
            chars[ch] = chars.get(ch, 0) + 1
        for i, ch in enumerate(s):
            ch = ord(ch)
            if chars[ch] == 1:
                return i
        return -1
