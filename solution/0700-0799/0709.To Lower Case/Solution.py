class Solution:
    def toLowerCase(self, s: str) -> str:
        return ''.join(
            [chr(ord(c) | 32) if ord('A') <= ord(c) <= ord('Z') else c for c in s]
        )
