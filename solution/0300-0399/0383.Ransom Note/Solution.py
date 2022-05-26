class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        chars = {}
        for i in magazine:
            chars[i] = chars.get(i, 0) + 1
        for i in ransomNote:
            if not chars.get(i):
                return False
            chars[i] -= 1
        return True
