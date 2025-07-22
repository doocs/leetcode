class Solution:
    def reorderSpaces(self, text: str) -> str:
        spaces = text.count(" ")
        words = text.split()
        if len(words) == 1:
            return words[0] + " " * spaces
        cnt, mod = divmod(spaces, len(words) - 1)
        return (" " * cnt).join(words) + " " * mod
