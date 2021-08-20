class Solution:
    def reverseWords(self, s: str) -> str:
        return ' '.join([t[::-1] for t in s.split(' ')])
