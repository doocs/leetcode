class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        S = S[:length] if length < len(S) else S
        return S.replace(' ', '%20')