class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        return "".join(["%20" if c == " " else c for c in S[:length]])
