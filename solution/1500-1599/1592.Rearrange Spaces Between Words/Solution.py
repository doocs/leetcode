class Solution:
    def reorderSpaces(self, text: str) -> str:
        cnt = text.count(' ')
        words = text.split()
        m = len(words) - 1
        if m == 0:
            return words[0] + ' ' * cnt
        return (' ' * (cnt // m)).join(words) + ' ' * (cnt % m)
